# Mobile Auth Service - Code Review Exercise

A mini Spring Boot authentication service with **25 intentional security and business flaws** for interview code review practice.

## Quick Start

### Building
```bash
mvn clean compile
```

### Running
```bash
mvn spring-boot:run
```

Service runs on `http://localhost:8080/api/v1`

## API Endpoints

### 1. Login
```bash
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "testpass"
}
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9...",
  "tokenType": "Bearer"
}
```

**Test Credentials:**
- `testuser` / `testpass`
- `admin` / `admin123`

### 2. Get User
```bash
GET /api/v1/auth/user
Authorization: Bearer <token>
```

### 3. Refresh Token
```bash
POST /api/v1/auth/refresh
Authorization: Bearer <token>
```

### 4. Validate Token
```bash
POST /api/v1/auth/validate?token=<token>
```

---

## Exercise Instructions for Candidates

### Goal
Review the provided source code and identify **security concerns, design flaws, and operational issues**.

### Time
45-60 minutes for thorough review + 15 minutes for discussion

### What to Document
For each issue found, provide:
1. **File & Line Location**
2. **What's the problem?**
3. **Why is it a security/business concern?**
4. **How would you fix it?**

### Files to Review
```
src/main/java/com/avb/mobileauth/
├── controller/
│   └── AuthController.java       (Entry points)
├── service/
│   └── AuthService.java           (Business logic)
├── security/
│   ├── JwtTokenProvider.java      (Token handling)
│   └── SecurityConfig.java        (Security config)
├── model/
│   └── User.java
├── dto/
│   ├── LoginRequest.java
│   └── TokenResponse.java
└── repository/
    └── UserRepository.java

src/main/resources/
└── application.yml                (Configuration)
```

### Hints (Don't read unless stuck)
- Start with the controller (entry points)
- Check how passwords are handled
- Review JWT configuration
- Look at CORS settings
- Consider what happens on restart

---

## Issue Categories

| Category | Count | Severity Mix |
|----------|-------|--------------|
| **Cryptography & Passwords** | 5 | 3 CRITICAL, 2 HIGH |
| **Token Management** | 8 | 3 CRITICAL, 3 HIGH, 2 MEDIUM |
| **Input Validation** | 5 | 1 HIGH, 4 MEDIUM |
| **Error Handling** | 3 | 1 HIGH, 2 MEDIUM |
| **CORS & Transport** | 2 | 2 CRITICAL |
| **Architecture** | 2 | 2 MEDIUM |
| **Configuration** | 1 | 1 CRITICAL |
| **Data Management** | 1 | 1 MEDIUM |

**Total: 25 issues**

---

## Reference Documentation

- See `/Users/napat.nat/Documents/ObsidianVaults/avb/mobile-auth-code-review-exercise.md` for **detailed issue descriptions and fixes**
- See `/Users/napat.nat/Documents/ObsidianVaults/avb/mobile-auth-checklist.md` for **quick reference checklist**

---

## Key Learning Areas

This exercise covers:

1. **Authentication Security**
   - Password hashing and comparison
   - Token generation and validation
   - Session management

2. **Cryptography**
   - JWT best practices
   - Key management and rotation
   - Secret storage

3. **API Security**
   - Input validation
   - Error handling
   - CORS policies
   - Token transmission

4. **Operational Security**
   - Logging and monitoring
   - Rate limiting
   - Distributed system concerns

5. **Secure Coding**
   - Avoiding common vulnerabilities
   - Defense in depth
   - Fail-safe defaults

---

## Discussion Topics

After review, interviewer should explore:

- **Severity & Impact**: Which issues are most critical and why?
- **Prioritization**: How would you fix this incrementally?
- **Tradeoffs**: How do you balance security with performance?
- **Deployment**: How does this change with multiple instances?
- **Standards**: What frameworks/guidelines would you reference?
- **Testing**: How would you verify your fixes?

---

## For Interviewers

### Setup
1. Clone/run project
2. Have candidate review code (no running/testing needed for review, but can demo if helpful)
3. Track issues found
4. Discuss fixes and trade-offs
5. Evaluate depth of security knowledge

### Scoring
- **Excellent (18+ issues)**: Strong security engineering mindset
- **Good (12-17 issues)**: Solid understanding of auth patterns
- **Acceptable (8-11 issues)**: Foundational knowledge present
- **Needs improvement (<8 issues)**: Recommend security training

### Red Flags
- Doesn't mention plaintext passwords
- Misses CORS misconfiguration
- Doesn't question the weak JWT secret
- No mention of rate limiting for brute force

### Green Flags
- Asks about deployment topology (single vs. multiple instances)
- References OWASP or CWE
- Proposes distributed solutions (Redis, database)
- Questions business requirements (token lifetime, refresh strategy)

---

## Answers & Solutions

Full solutions with explanations are available in the accompanying documentation:
- **Detailed fixes**: `mobile-auth-code-review-exercise.md`
- **Issue checklist**: `mobile-auth-checklist.md`

(These should be reviewed by interviewer only before conducting interview)

---

## Contact & Questions

For interview process questions or clarifications on this exercise, refer to the documentation in the Obsidian vault.

Good luck! 🚀
