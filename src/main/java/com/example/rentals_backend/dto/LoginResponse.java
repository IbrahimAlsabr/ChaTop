package com.example.rentals_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) for representing authentication response.")
public class LoginResponse {
	@Schema(description = "The token of the user.")
	private String token;
}
