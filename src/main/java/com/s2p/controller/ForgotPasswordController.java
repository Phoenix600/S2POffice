package com.s2p.controller;

import com.s2p.services.impl.OtpService;
import com.s2p.util.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailUtility emailUtility;

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email) {
        String otp = otpService.generateOtp(email);
        emailUtility.sendOtpEmail(email, otp);
        return "OTP sent to " + email;
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        return isValid ? "OTP verified" : "Invalid or expired OTP";
    }
}
