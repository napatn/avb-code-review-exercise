package com.avb.mobileauth.downstream;

import com.avb.mobileauth.model.PinVerificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserPinService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PIN_VERIFICATION_ENDPOINT = "https://api.example.com/verify-pin";

    public PinVerificationResponse verifyPin(String userId, String pin) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("userId", userId);
            request.put("pin", pin);

            Map<String, Object> response = restTemplate.postForObject(
                PIN_VERIFICATION_ENDPOINT,
                request,
                Map.class
            );

            boolean verified = (boolean) response.get("verified");
            String message = (String) response.get("message");

            return new PinVerificationResponse(verified, message);

        } catch (Exception e) {
            return new PinVerificationResponse(false, "PIN verification service unavailable");
        }
    }
}

