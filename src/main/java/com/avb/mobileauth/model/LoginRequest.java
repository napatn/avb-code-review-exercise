package com.avb.mobileauth.model;

public class LoginRequest {
    private String userId;
    private String pin;
    private String otpCode;
    private int attemptCount;

    public LoginRequest() {
    }

    public LoginRequest(String userId, String pin, String otpCode, int attemptCount) {
        this.userId = userId;
        this.pin = pin;
        this.otpCode = otpCode;
        this.attemptCount = attemptCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }
}
