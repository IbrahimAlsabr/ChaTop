package com.example.rentals_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Data Transfer Object (DTO) for representing signup information.")
public class SignupRequest {

	@Schema(description = "The email address of the user.", example = "user@example.com")
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;

	@Schema(description = "The name of the user.", example = "User Example")
	@NotBlank(message = "Name is required")
	private String name;

	@Schema(description = "The password of the user.", example = "password")
	@NotBlank(message = "Password is required")
	private String password;
}
