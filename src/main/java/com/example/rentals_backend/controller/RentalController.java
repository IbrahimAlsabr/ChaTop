package com.example.rentals_backend.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.rentals_backend.dto.ApiMessageResponse;
import com.example.rentals_backend.dto.RentalDetailResponse;
import com.example.rentals_backend.dto.RentalResponse;
import com.example.rentals_backend.dto.RentalsResponse;
import com.example.rentals_backend.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/rentals")
@Tag(name = "Rental", description = "Rental API")
@SecurityRequirement(name = "bearerAuth")
public class RentalController {

	private final RentalService rentalService;

	public RentalController(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@Operation(summary = "Get all rentals", description = "Get all rentals")
	@GetMapping
	public RentalsResponse getAllRentals() {
		List<RentalResponse> rentals = rentalService.getAllRentals();
		return new RentalsResponse(rentals);
	}

	@Operation(summary = "Get rental by id", description = "Get rental by id")
	@GetMapping("/{id}")
	public RentalDetailResponse getRentalById(@PathVariable Long id) {
		return rentalService.getRentalById(id);
	}

	@Operation(summary = "Create rental", description = "Create rental")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiMessageResponse createRental(
			@RequestParam String name,
			@RequestParam int surface,
			@RequestParam int price,
			@RequestParam MultipartFile picture,
			@RequestParam String description,
			@AuthenticationPrincipal Jwt jwt) {

		String email = jwt.getSubject();

		rentalService.createRental(email, name, surface, price, picture, description);
		return new ApiMessageResponse("Rental created !");
	}

	@Operation(summary = "Update rental", description = "Update rental")
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiMessageResponse updateRental(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam int surface,
			@RequestParam int price,
			@RequestParam(required = false) MultipartFile picture,
			@RequestParam String description) {

		rentalService.updateRental(id, name, surface, price, picture, description);
		return new ApiMessageResponse("Rental updated !");
	}
}
