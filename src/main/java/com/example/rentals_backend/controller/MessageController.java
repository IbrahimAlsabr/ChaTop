package com.example.rentals_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentals_backend.dto.MessageRequest;
import com.example.rentals_backend.dto.MessageResponse;
import com.example.rentals_backend.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Message", description = "Message API")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
 
	@Operation(summary = "Create message", description = "Create message")
	@PostMapping
	public MessageResponse createMessage(@Valid @RequestBody MessageRequest messageRequest) {
		return messageService.createMessage(messageRequest);
	}

}
