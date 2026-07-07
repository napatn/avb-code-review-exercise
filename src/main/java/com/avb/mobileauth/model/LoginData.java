package com.avb.mobileauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginData {
    private String sessionId;
    private CustomerProfile userProfile;

    public LoginData() {
    }

    public LoginData(String sessionId, CustomerProfile userProfile) {
        this.sessionId = sessionId;
        this.userProfile = userProfile;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public CustomerProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(CustomerProfile userProfile) {
        this.userProfile = userProfile;
    }
}
