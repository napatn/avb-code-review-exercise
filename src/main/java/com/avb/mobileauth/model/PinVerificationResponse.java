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
}
