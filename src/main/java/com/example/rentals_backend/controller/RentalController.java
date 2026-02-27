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

import com.example.rentals_backend.dto.RentalResponse;
import com.example.rentals_backend.entity.RentalEntity;
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
	public List<RentalResponse> getAllRentals() {
		return rentalService.getAllRentals();
	}

	@Operation(summary = "Get rental by id", description = "Get rental by id")
	@GetMapping("/{id}")
	public RentalResponse getRentalById(@PathVariable Long id) {
		return rentalService.getRentalById(id);
	}

	@Operation(summary = "Create rental", description = "Create rental")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RentalResponse createRental(
			@RequestParam String name,
			@RequestParam int surface,
			@RequestParam int price,
			@RequestParam(required = false) MultipartFile picture,
			@RequestParam String description,
			@AuthenticationPrincipal Jwt jwt) {

		String email = jwt.getSubject();

		RentalEntity rental = rentalService.createRental(email, name, surface, price, picture, description);
		return new RentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(),
				rental.getPicture(), rental.getDescription());
	}

	@Operation(summary = "Update rental", description = "Update rental")
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public RentalResponse updateRental(
			@PathVariable Long id,
			@RequestParam String name,
			@RequestParam int surface,
			@RequestParam int price,
			@RequestParam(required = false) MultipartFile picture,
			@RequestParam String description) {

		RentalEntity rental = rentalService.updateRental(id, name, surface, price, picture, description);
		return new RentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(),
				rental.getPicture(), rental.getDescription());
	}
}
