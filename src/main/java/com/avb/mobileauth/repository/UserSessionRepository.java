package com.avb.mobileauth.repository;

import com.avb.mobileauth.model.UserSession;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserSessionRepository {

    private Map<String, UserSession> sessions = new HashMap<>();

    public UserSession createSession(String userId) {
        String sessionId = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime expiresAt = createdAt.plusHours(24);

        UserSession session = new UserSession(
            sessionId,
            userId,
            createdAt,
            expiresAt,
            true
        );

        sessions.put(sessionId, session);
        return session;
    }

    public Optional<UserSession> findBySessionId(String sessionId) {
        return Optional.ofNullable(sessions.get(sessionId));
    }

    public Optional<UserSession> findByUserId(String userId) {
        return sessions.values().stream()
            .filter(session -> session.getUserId().equals(userId) && session.isActive())
            .findFirst();
    }

    public void deleteSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public void invalidateSession(String sessionId) {
        sessions.computeIfPresent(sessionId, (key, session) -> {
            session.setActive(false);
            return session;
        });
    }
}
