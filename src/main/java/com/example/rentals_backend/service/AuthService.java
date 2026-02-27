package com.example.rentals_backend.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.rentals_backend.config.SecurityConfig;
import com.example.rentals_backend.dto.LoginRequest;
import com.example.rentals_backend.dto.MeResponse;
import com.example.rentals_backend.dto.SignupRequest;
import com.example.rentals_backend.dto.SignupResponse;
import com.example.rentals_backend.dto.LoginResponse;
import com.example.rentals_backend.entity.UserEntity;
import com.example.rentals_backend.repository.UserRepository;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JWTTokenService jwtTokenService;
	private final SecurityConfig securityConfig;

	public AuthService(
			UserRepository userRepository,
			AuthenticationManager authenticationManager,
			JWTTokenService jwtTokenService,
			SecurityConfig securityConfig) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenService = jwtTokenService;
		this.securityConfig = securityConfig;
	}

	public LoginResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getLogin(),
						loginRequest.getPassword()));

		return new LoginResponse(jwtTokenService.generateToken(authentication));
	}

	public SignupResponse signup(SignupRequest signupRequest) {
		UserEntity user = new UserEntity();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(securityConfig.passwordEncoder().encode(signupRequest.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);

		return new SignupResponse(user.getId(), user.getEmail(), user.getName());
	}

	public MeResponse me(String username) {
		UserEntity user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new MeResponse(user.getId(), user.getEmail(), user.getName());
 
	}
}
