package com.example.rentals_backend.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Wrapper response for rentals list.")
public class RentalsResponse {
	@Schema(description = "List of rentals.")
	private List<RentalResponse> rentals;
}

