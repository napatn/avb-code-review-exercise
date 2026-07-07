package com.avb.mobileauth.repository;

import com.avb.mobileauth.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("testuser", new User("1", "testuser", "testpass", "test@example.com", true));
        users.put("admin", new User("2", "admin", "admin123", "admin@example.com", true));
    }

    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return users.get(username);
    }

    public void save(User user) {
        if (user != null && user.getUsername() != null) {
            users.put(user.getUsername(), user);
        }
    }

    public Optional<User> findById(String id) {
        return users.values().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}
