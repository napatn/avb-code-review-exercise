package com.avb.mobileauth.model;

public class LoginResponse {
    private boolean success;
    private String message;
    private String authToken;
    private CustomerProfile userProfile;
    private String debugInfo;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, String message, String authToken, CustomerProfile userProfile, String debugInfo) {
        this.success = success;
        this.message = message;
        this.authToken = authToken;
        this.userProfile = userProfile;
        this.debugInfo = debugInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public CustomerProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(CustomerProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }
}
