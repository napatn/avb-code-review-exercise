package com.avb.mobileauth.model;

public class KYCResponse {
    private String kycLevel;
    private boolean verified;
    private String message;

    public KYCResponse() {
    }

    public KYCResponse(String kycLevel, boolean verified, String message) {
        this.kycLevel = kycLevel;
        this.verified = verified;
        this.message = message;
    }

    public String getKycLevel() {
        return kycLevel;
    }

    public void setKycLevel(String kycLevel) {
        this.kycLevel = kycLevel;
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
