package com.avb.mobileauth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginData {
    private CustomerProfile userProfile;

    public LoginData() {
    }

    public LoginData(CustomerProfile userProfile) {
        this.userProfile = userProfile;
    }

    public CustomerProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(CustomerProfile userProfile) {
        this.userProfile = userProfile;
    }
}
