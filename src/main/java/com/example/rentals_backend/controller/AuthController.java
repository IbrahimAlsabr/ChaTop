package com.example.rentals_backend.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentals_backend.dto.LoginResponse;
import com.example.rentals_backend.dto.MeResponse;
import com.example.rentals_backend.dto.SignupRequest;
import com.example.rentals_backend.dto.SignupResponse;
import com.example.rentals_backend.dto.LoginRequest;
import com.example.rentals_backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/auth")
@Tag(name = "User", description = "User API")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@Operation(summary = "Login API", description = "Login API")
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest userDTO) {
		return authService.login(userDTO);
	}

	@Operation(summary = "Register API", description = "Register API")
	@PostMapping("/register")
	public SignupResponse signup(@RequestBody SignupRequest signupRequest) {
		return authService.signup(signupRequest);
	}

	@Operation(summary = "Me API", description = "Me API")
	@GetMapping("/me")
	@SecurityRequirement(name = "bearerAuth")
	public MeResponse me(@AuthenticationPrincipal Jwt jwt) {
		String email = jwt.getSubject();
		return authService.me(email);
	}
}
