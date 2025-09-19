package com.s2p.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model representing OTP (One-Time Password) data")
public class OtpData {

    @Schema(description = "One-Time Password value", example = "482913")
    private String otp;

    @Schema(description = "Expiry time of the OTP in epoch milliseconds", example = "1727012345678")
    private long expiryTime;

    public OtpData(String otp, long expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }

    public String getOtp() {
        return otp;
    }

    public long getExpiryTime() {
        return expiryTime;
    }
}
