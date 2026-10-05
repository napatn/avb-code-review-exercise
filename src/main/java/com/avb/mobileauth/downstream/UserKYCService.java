package com.avb.mobileauth.downstream;

import com.avb.mobileauth.model.KYCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserKYCService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String KYC_VERIFICATION_ENDPOINT = "https://api.example.com/verify-kyc";

    public KYCResponse verifyKYC(String userId) {
        try {
            Map<String, Object> request = new HashMap<>();
            request.put("userId", userId);

            Map<String, Object> response = restTemplate.postForObject(
                KYC_VERIFICATION_ENDPOINT,
                request,
                Map.class
            );

            String kycLevel = (String) response.get("kycLevel");
            boolean verified = (boolean) response.get("verified");
            String message = (String) response.get("message");

            return new KYCResponse(kycLevel, verified, message);

        } catch (Exception e) {
            return new KYCResponse(null, false, "KYC verification service unavailable");
        }
    }
}
