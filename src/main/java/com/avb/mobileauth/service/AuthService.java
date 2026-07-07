package com.avb.mobileauth.service;

import com.avb.mobileauth.dto.TokenResponse;
import com.avb.mobileauth.security.JwtTokenProvider;
import com.avb.mobileauth.repository.UserRepository;
import com.avb.mobileauth.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private static final ConcurrentHashMap<String, Long> tokenBlacklist = new ConcurrentHashMap<>();
    private static final long TOKEN_BLACKLIST_CLEANUP_INTERVAL = 3600000;
    private static long lastCleanup = System.currentTimeMillis();

    public TokenResponse authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Authentication failed");
        }

        if (!user.isActive()) {
            throw new IllegalArgumentException("Account inactive");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Authentication failed");
        }

        String token = tokenProvider.generateToken(username);
        return new TokenResponse(token, "Bearer");
    }

    public TokenResponse refreshToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token is required");
        }

        if (!validateToken(token)) {
            throw new IllegalArgumentException("Token validation failed");
        }

        String username = tokenProvider.extractUsername(token);
        String newToken = tokenProvider.generateToken(username);
        return new TokenResponse(newToken, "Bearer");
    }

    public boolean validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }
        
        if (tokenBlacklist.containsKey(token)) {
            return false;
        }
        
        return tokenProvider.validateToken(token);
    }

    public String extractUserId(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token is required");
        }
        return tokenProvider.extractUsername(token);
    }

    public void logout(String token) {
        if (token != null && !token.isEmpty()) {
            tokenBlacklist.put(token, System.currentTimeMillis());
            cleanupBlacklist();
        }
    }

    private void cleanupBlacklist() {
        long now = System.currentTimeMillis();
        if (now - lastCleanup > TOKEN_BLACKLIST_CLEANUP_INTERVAL) {
            tokenBlacklist.entrySet().removeIf(entry -> 
                (now - entry.getValue()) > TOKEN_BLACKLIST_CLEANUP_INTERVAL
            );
            lastCleanup = now;
        }
    }
}
