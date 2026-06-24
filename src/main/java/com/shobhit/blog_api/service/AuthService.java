package com.shobhit.blog_api.service;

import com.shobhit.blog_api.dto.request.LoginRequest;
import com.shobhit.blog_api.dto.request.RegisterRequest;
import com.shobhit.blog_api.entity.Role;
import com.shobhit.blog_api.entity.User;
import com.shobhit.blog_api.repository.UserRepository;
import com.shobhit.blog_api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public void register(RegisterRequest request){
        User user=new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())
        );
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow();
        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );
        if (!matches) {
            throw new RuntimeException(
                    "Invalid credentials"
            );
        }

        return jwtService.generateToken(
                user.getEmail()
        );
    }



}
