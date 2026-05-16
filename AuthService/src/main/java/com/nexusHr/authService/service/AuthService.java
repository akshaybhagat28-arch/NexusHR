package com.nexusHr.authService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexusHr.authService.entity.AuthResponse;
import com.nexusHr.authService.entity.LoginRequest;
import com.nexusHr.authService.entity.RefreshToken;
import com.nexusHr.authService.entity.SignupRequest;
import com.nexusHr.authService.entity.User;
import com.nexusHr.authService.repository.RefreshTokenRepository;
import com.nexusHr.authService.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtService jwtService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RefreshTokenRepository refreshRepo;
	@Autowired
	PasswordEncoder encoder;
	
	AuthResponse authResponse = new AuthResponse();

	public String signup(SignupRequest req) {

		User user = new User();
		user.setUsername(req.getUsername());
		user.setPassword(encoder.encode(req.getPassword()));
		user.setRole(req.getRole());

		userRepo.save(user);
		return "User registered";
	}

	public AuthResponse login(LoginRequest req) {
		
		

		authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

		User user = userRepo.findByUsername(req.getUsername());

		String accessToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().name()))));

		String refreshToken = UUID.randomUUID().toString();

		RefreshToken rt = new RefreshToken();
		rt.setToken(refreshToken);
		rt.setUser(user);
		rt.setExpiryDate(LocalDateTime.now().plusDays(7));

		refreshRepo.save(rt);
		authResponse.setAccessToken(accessToken);
		authResponse.setRefreshToken(refreshToken);
		authResponse.setRole(user.getRole());

		return authResponse;
	}

	public AuthResponse refresh(String refreshToken) {

		RefreshToken token = refreshRepo.findByToken(refreshToken)
				.orElseThrow(() -> new RuntimeException("Invalid token"));

		if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("Expired refresh token");
		}

		String accessToken = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
				token.getUser().getUsername(), token.getUser().getPassword(),
				List.of(new SimpleGrantedAuthority(token.getUser().getRole().name()))));
		authResponse.setAccessToken(accessToken);
		authResponse.setRefreshToken(refreshToken);

		return authResponse;
	}

	@Modifying
	public void logout(String refreshToken) {
		refreshRepo.deleteByToken(refreshToken);
	}
}
