package com.avb.mobileauth.controller;

import com.avb.mobileauth.downstream.UserProfileService;
import com.avb.mobileauth.downstream.UserPinService;
import com.avb.mobileauth.model.ApiResponse;
import com.avb.mobileauth.model.LoginData;
import com.avb.mobileauth.model.LoginRequest;
import com.avb.mobileauth.model.PinVerificationResponse;
import com.avb.mobileauth.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserPinService userPinService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginData>> login(@RequestBody LoginRequest request) {
        try {
            String userId = request.getUserId();
            String pin = request.getPin();
            String otpCode = request.getOtpCode();

            if (request.getAttemptCount() > 3) {
                ApiResponse<LoginData> response = new ApiResponse<>("ERR_ACCOUNT_LOCKED", "Account locked due to multiple failed attempts");
                return ResponseEntity.ok(response);
            }

            PinVerificationResponse pinVerification = userPinService.verifyPin(userId, pin);

            if (!pinVerification.isVerified()) {
                ApiResponse<LoginData> response = new ApiResponse<>("ERR_INVALID_PIN", pinVerification.getMessage());
                return ResponseEntity.ok(response);
            }

            var userProfile = userProfileService.getUserProfile(userId);

            var session = userSessionRepository.createSession(userId);

            LoginData loginData = new LoginData(session.getSessionId(), userProfile);
            ApiResponse<LoginData> response = new ApiResponse<>("SUCCESS", "Login successful", loginData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse<LoginData> response = new ApiResponse<>("ERR_INTERNAL_ERROR", "An error occurred during login");
            return ResponseEntity.ok(response);
        }
    }
}
