package com.avb.mobileauth.downstream;

import com.avb.mobileauth.model.PinVerificationResponse;

public class UserPinService {

    public PinVerificationResponse verifyPin(String userId, String pin) {
        if (pin == null || pin.isEmpty()) {
            return new PinVerificationResponse(
                false,
                "PIN is required",
                3,
                false
            );
        }

        if (pin.equals("111111")) {
            return new PinVerificationResponse(
                true,
                "PIN verified successfully",
                3,
                false
            );
        } else {
            return new PinVerificationResponse(
                false,
                "Invalid PIN",
                2,
                false
            );
        }
    }
}
