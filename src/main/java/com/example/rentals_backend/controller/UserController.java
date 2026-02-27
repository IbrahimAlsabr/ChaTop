package com.example.rentals_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentals_backend.dto.UserDTO;
import com.example.rentals_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "User API")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Get user by id", description = "Get user by id")
	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable long id) {
		return userService.getUserById(id);
	}
}
