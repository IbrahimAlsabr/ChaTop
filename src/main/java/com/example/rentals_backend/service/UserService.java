package com.example.rentals_backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.rentals_backend.dto.UserDTO;
import com.example.rentals_backend.entity.UserEntity;
import com.example.rentals_backend.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDTO getUserById(long id) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setCreated_at(toLocalDate(user.getCreatedAt()));
		dto.setUpdated_at(toLocalDate(user.getUpdatedAt()));
		return dto;
	}

	private static LocalDate toLocalDate(LocalDateTime value) {
		return value == null ? null : value.toLocalDate();
	}
}
