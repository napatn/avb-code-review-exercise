package com.avb.mobileauth.model;

public class PinVerificationResponse {
    private boolean verified;
    private String message;

    public PinVerificationResponse() {
    }

    public PinVerificationResponse(boolean verified, String message) {
        this.verified = verified;
        this.message = message;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
