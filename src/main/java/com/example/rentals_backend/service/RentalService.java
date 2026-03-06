package com.example.rentals_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.rentals_backend.dto.RentalDetailResponse;
import com.example.rentals_backend.dto.RentalResponse;
import com.example.rentals_backend.entity.RentalEntity;
import com.example.rentals_backend.repository.RentalRepository;
import com.example.rentals_backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalService {

	private final RentalRepository rentalRepository;
	private final UserRepository userRepository;

	@Value("${app.upload.dir:uploads}")
	private String uploadDir;

	@Value("${app.public-base-url:http://localhost:8080}")
	private String publicBaseUrl;

	public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.rentalRepository = rentalRepository;
	}

	public List<RentalResponse> getAllRentals() {
		List<RentalEntity> rentals = rentalRepository.findAll();
		List<RentalResponse> rentalResponses = new ArrayList<>();
		for (RentalEntity rental : rentals) {
			rentalResponses.add(
					new RentalResponse(
							rental.getId(),
							rental.getName(),
							rental.getSurface(),
							(int) rental.getPrice(),
							toPictureUrl(rental.getPicture()),
							rental.getDescription(),
							rental.getOwner() == null ? null : rental.getOwner().getId(),
							toLocalDate(rental.getCreatedAt()),
							toLocalDate(rental.getUpdatedAt())));
		}
		return rentalResponses;
	}

	public RentalDetailResponse getRentalById(Long id) {
		RentalEntity rental = rentalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental not found with id " + id));

		return new RentalDetailResponse(
				rental.getId(),
				rental.getName(),
				rental.getSurface(),
				(int) rental.getPrice(),
				rental.getPicture() == null ? List.of() : List.of(toPictureUrl(rental.getPicture())),
				rental.getDescription(),
				rental.getOwner() == null ? null : rental.getOwner().getId(),
				toLocalDate(rental.getCreatedAt()),
				toLocalDate(rental.getUpdatedAt()));

	}

	public RentalEntity updateRental(
			Long id,
			String name,
			int surface,
			int price,
			MultipartFile picture,
			String description) {

		RentalEntity rental = rentalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Rental not found with id " + id));

		rental.setName(name);
		rental.setSurface(surface);
		rental.setPrice(price);
		rental.setDescription(description);
		rental.setUpdatedAt(LocalDateTime.now());

		if (picture != null && !picture.isEmpty()) {
			rental.setPicture(storePicture(picture));
		}

		return rentalRepository.save(rental);
	}

	public RentalEntity createRental(
			String email,
			String name,
			int surface,
			int price,
			MultipartFile picture,
			String description) {
		RentalEntity rental = new RentalEntity();
		rental.setOwner(userRepository.findByEmail(email).orElse(null));
		rental.setName(name);
		rental.setSurface(surface);
		rental.setPrice(price);
		rental.setDescription(description);
		rental.setCreatedAt(LocalDateTime.now());
		rental.setUpdatedAt(LocalDateTime.now());
		if (picture == null || picture.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Picture is required");
		}
		rental.setPicture(storePicture(picture));

		return rentalRepository.save(rental);
	}

	private String storePicture(MultipartFile picture) {
		try {
			Path uploadPath = Paths.get(uploadDir);
			Files.createDirectories(uploadPath);

			String originalFilename = StringUtils.cleanPath(
					picture.getOriginalFilename() == null ? "picture" : picture.getOriginalFilename());
			String extension = StringUtils.getFilenameExtension(originalFilename);
			String fileName = extension == null || extension.isBlank()
					? UUID.randomUUID().toString()
					: UUID.randomUUID() + "." + extension;
			Path destination = uploadPath.resolve(fileName);
			Files.copy(picture.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

			return "/uploads/" + fileName;
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to store picture", e);
		}
	}

	private String toPictureUrl(String picturePath) {
		if (picturePath == null || picturePath.isBlank()) {
			return picturePath;
		}
		if (picturePath.startsWith("http://") || picturePath.startsWith("https://")) {
			return picturePath;
		}
		return publicBaseUrl + picturePath;
	}

	private static LocalDate toLocalDate(LocalDateTime value) {
		return value == null ? null : value.toLocalDate();
	}

}
