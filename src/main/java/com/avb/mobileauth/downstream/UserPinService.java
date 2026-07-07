package com.avb.mobileauth.downstream;

import com.avb.mobileauth.model.PinVerificationResponse;
import org.springframework.stereotype.Service;

@Service
public class UserPinService {

    public PinVerificationResponse verifyPin(String userId, String pin) {
        if (pin == null || pin.isEmpty()) {
            return new PinVerificationResponse(
                false,
                "PIN is required"
            );
        }

        if (pin.equals("111111")) {
            return new PinVerificationResponse(
                true,
                "PIN verified successfully"
            );
        } else {
            return new PinVerificationResponse(
                false,
                "Invalid PIN"
            );
        }
    }
}
