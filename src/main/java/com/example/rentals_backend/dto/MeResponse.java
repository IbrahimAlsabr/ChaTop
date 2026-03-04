package com.example.rentals_backend.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Data Transfer Object (DTO) for representing signup response.")
public class MeResponse {
	@Schema(description = "The unique identifier of the user.", example = "0")
	private Long id;

	@Schema(description = "The name of the user.", example = "User Example")
	private String name;

	@Schema(description = "The email address of the user.", example = "user@example.com")
	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@Schema(description = "The date when the user was created.", example = "2022/02/02")
	private LocalDate created_at;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@Schema(description = "The date when the user was last updated.", example = "2022/08/02")
	private LocalDate updated_at;
}
