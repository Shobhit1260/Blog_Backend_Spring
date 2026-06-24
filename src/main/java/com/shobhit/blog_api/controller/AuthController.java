package com.shobhit.blog_api.controller;

import com.shobhit.blog_api.dto.request.LoginRequest;
import com.shobhit.blog_api.dto.request.RegisterRequest;
import com.shobhit.blog_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "User registered successfully"
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {
        String token =
                authService.login(request);
        return ResponseEntity.ok(
                Map.of(
                        "token",
                        token
                )
        );
    }
}
