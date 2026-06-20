package com.nexusHr.authService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexusHr.authService.entity.AuthResponse;
import com.nexusHr.authService.entity.LoginRequest;
import com.nexusHr.authService.entity.RefreshRequest;
import com.nexusHr.authService.entity.SignupRequest;
import com.nexusHr.authService.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired 
    AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        return authService.signup(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest req) {
        return authService.refresh(req.getRefreshToken());
    }

    @PostMapping("/logout")
    public String logout(@RequestBody RefreshRequest req) {
        authService.logout(req.getRefreshToken());
        return "Logged out successfully";
    }
}
