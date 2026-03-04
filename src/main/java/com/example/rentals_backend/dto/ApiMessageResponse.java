package com.example.rentals_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Simple message response.")
public class ApiMessageResponse {
	@Schema(description = "Message text.")
	private String message;
}

