package com.unicomer.api.sso.services;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import com.unicomer.api.sso.models.Otp;
import com.unicomer.api.sso.models.dto.otps.ValidateResponseDto;
import com.unicomer.api.sso.models.dto.sms.SmsRequestDto;
import com.unicomer.api.sso.models.dto.sms.SmsResponseDto;
import com.unicomer.api.sso.repositories.OtpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class OtpService {
	@Autowired
	OtpRepository otpRepository;

	// @Autowired
	WebClient webClient;

	public OtpService() {
		webClient = WebClient.create("https://smscorp.tigo.com.sv/wsAPISmsCorp.asmx");
	}

	public ValidateResponseDto validate(String employeeCode, String oneTimePassword, Integer idApp) {
		Otp otp = otpRepository.findFirstByEmployeeCodeAndIdAppOrderByOtpRequestedTimeDesc(employeeCode, idApp);
		if (!Objects.isNull(otp)) {
			String encodedOneTimePassword = otp.getOneTimePassword();

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if (passwordEncoder.matches(oneTimePassword, encodedOneTimePassword)) {
				return new ValidateResponseDto(true, "Código válido", "");
			}
		}
		return new ValidateResponseDto(false, "Código inválido", "Este código no coincide con el enviado");
	}

	public Otp saveOtp(Otp otp) throws IOException {
		Integer oneTimePassword = generateCode();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedOneTimePassword = passwordEncoder.encode(oneTimePassword.toString());

		SmsRequestDto smsRequestDto = new SmsRequestDto();
		smsRequestDto.setUsuario("unicomergt");
		smsRequestDto.setContrasena("un1@234");
		smsRequestDto.setNumero(otp.getCountryCode().toString() + otp.getPhone().toString());
		smsRequestDto.setMensaje(
				"Grupo Unicomer\r\n\r\nCodigo de Autenticacion: "
						+ oneTimePassword
						+ "\r\n\r\nNo compartas este codigo con nadie.");
		smsRequestDto.setRemitente("Unicomer");

		sendSmsOTP(smsRequestDto).subscribe();

		otp.setOneTimePassword(encodedOneTimePassword);
		otp.setOtpRequestedTime(new Date());
		return otpRepository.save(otp);
	}

	private Integer generateCode() {
		Random random = new Random();
		Integer min = 100000;
		Integer max = 999999;
		return random.nextInt(max - min + 1) + min;
	}

	public Mono<SmsResponseDto> sendSmsOTP(SmsRequestDto smsRequestDto) {
		try {
			System.out.println(smsRequestDto);
			return webClient.post()
					.uri("/enviarSMS")
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(smsRequestDto), SmsRequestDto.class)
					.retrieve()
					.bodyToMono(SmsResponseDto.class);
		} catch (WebClientResponseException ex) {
			System.err.println("WebClientResponseException in retrieveSendSmsOTPTest " + ex.getRawStatusCode() + ": "
					+ ex.getResponseBodyAsString());
			throw ex;
		} catch (Exception ex) {
			System.err.println("Exception in retrieveSendSmsOTPTest " + ex);
			throw ex;
		}
	}
}