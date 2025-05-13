package com.example.ecoit2.controller;

import com.example.ecoit2.config.JwtService;
import com.example.ecoit2.entity.User;
import com.example.ecoit2.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent() ||
                userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Username or email already exists");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .isLoggedIn(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok(new RegisterResponse("register successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setLoggedIn(true);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse(jwtToken, "Login successfully");
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setLoggedIn(false);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        return ResponseEntity.ok("Logged out successfully");
    }
}

record RegisterRequest(String username, String email, String password) {}
record LoginRequest(String username, String password) {}
record LogoutRequest(String username) {}
record AuthResponse(String token, String message) {}
record RegisterResponse(String message) {}