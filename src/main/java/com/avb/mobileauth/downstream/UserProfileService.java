package com.avb.mobileauth.downstream;

import com.avb.mobileauth.model.CustomerProfile;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    public CustomerProfile getUserProfile(String userId) {
        return new CustomerProfile(
            userId,
            "John",
            "Doe",
            "john.doe@example.com",
            "+1-555-0100",
            "1990-05-15",
            "ACTIVE",
            "PERSONAL",
            "2023-01-10",
            "2026-07-07"
        );
    }
}
