package com.unicomer.api.sso.services;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import com.unicomer.api.sso.models.StoreOtp;
import com.unicomer.api.sso.models.dto.storeOtps.ValidateResponseDto;
import com.unicomer.api.sso.models.dto.sms.SmsRequestDto;
import com.unicomer.api.sso.models.dto.sms.SmsResponseDto;
import com.unicomer.api.sso.repositories.StoreOtpRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

@Service
public class StoreOtpService {
	@Autowired
	StoreOtpRepository storeOtpRepository;

	// @Autowired
	WebClient webClient;

	public StoreOtpService() {
		webClient = WebClient.create("https://smscorp.tigo.com.sv/wsAPISmsCorp.asmx");
	}

	public ValidateResponseDto validate(String employeeCode, String oneTimePassword) {
		StoreOtp storeOtp = storeOtpRepository.findFirstByEmployeeCodeOrderByOtpRequestedTimeDesc(employeeCode);
		if (!Objects.isNull(storeOtp)) {
			String encodedOneTimePassword = storeOtp.getOneTimePassword();

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			if (passwordEncoder.matches(oneTimePassword, encodedOneTimePassword)) {
				return new ValidateResponseDto(true, "Código válido", "");
			}
		}
		return new ValidateResponseDto(false, "Código inválido", "Este código no coincide con el enviado");
	}

	public StoreOtp saveStoreOtp(StoreOtp storeOtp) throws IOException {
		Integer oneTimePassword = generateCode();

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedOneTimePassword = passwordEncoder.encode(oneTimePassword.toString());

		SmsRequestDto smsRequestDto = new SmsRequestDto();
		smsRequestDto.setUsuario("unicomergt");
		smsRequestDto.setContrasena("un1@234");
		smsRequestDto.setNumero(storeOtp.getCountryCode().toString() + storeOtp.getPhone().toString());
		smsRequestDto.setMensaje(
				"Grupo Unicomer\r\n\r\nCodigo de Formulario de Estado Patrimonial: "
						+ oneTimePassword
						+ "\r\n\r\nNo compartas este codigo con nadie.");
		smsRequestDto.setRemitente("Unicomer");

		sendSmsOTP(smsRequestDto).subscribe();

		storeOtp.setOneTimePassword(encodedOneTimePassword);
		storeOtp.setOtpRequestedTime(new Date());
		return storeOtpRepository.save(storeOtp);
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