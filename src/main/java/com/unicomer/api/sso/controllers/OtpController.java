package com.unicomer.api.sso.controllers;

import java.io.IOException;

import com.unicomer.api.sso.models.Otp;
import com.unicomer.api.sso.models.dto.otps.ValidateResponseDto;
import com.unicomer.api.sso.models.dto.sms.SmsRequestDto;
import com.unicomer.api.sso.services.OtpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("/api/v1/otp")
public class OtpController {
    @Autowired
    OtpService otpService;

    @GetMapping("/validate")
    public ValidateResponseDto validate(@RequestParam String employeeCode, @RequestParam String oneTimePassword, @RequestParam Integer idApp) {
        return otpService.validate(employeeCode, oneTimePassword, idApp);
    }

    @PostMapping()
    Otp saveOtp(@RequestBody Otp otp)
            throws IOException {
        return otpService.saveOtp(otp);
    }

    @PostMapping("/test")
    public Otp sendSmsOTPTest(@RequestBody SmsRequestDto smsRequestDto)
            throws IOException {
        otpService.sendSmsOTP(smsRequestDto).subscribe();
        return new Otp();
    }
}