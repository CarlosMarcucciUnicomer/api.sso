package com.unicomer.api.sso.controllers;

import java.io.IOException;

import com.unicomer.api.sso.models.StoreOtp;
import com.unicomer.api.sso.models.dto.storeOtps.ValidateResponseDto;
import com.unicomer.api.sso.models.dto.sms.SmsRequestDto;
import com.unicomer.api.sso.services.StoreOtpService;

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
@RequestMapping("/api/v1/store-opt")
public class StoreOtpController {
    @Autowired
    StoreOtpService storeOtpService;

    @GetMapping("/validate")
    public ValidateResponseDto validate(@RequestParam String employeeCode, @RequestParam String oneTimePassword) {
        return storeOtpService.validate(employeeCode, oneTimePassword);
    }

    @PostMapping()
    StoreOtp saveHrPatrimonialStateOtp(@RequestBody StoreOtp storeOtp)
            throws IOException {
        return storeOtpService.saveStoreOtp(storeOtp);
    }

    @PostMapping("/test")
    public StoreOtp sendSmsOTPTest(@RequestBody SmsRequestDto smsRequestDto)
            throws IOException {
        storeOtpService.sendSmsOTP(smsRequestDto).subscribe();
        return new StoreOtp();
    }
}