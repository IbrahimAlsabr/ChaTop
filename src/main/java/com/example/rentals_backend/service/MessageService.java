package com.example.rentals_backend.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.rentals_backend.dto.ApiMessageResponse;
import com.example.rentals_backend.dto.MessageRequest;
import com.example.rentals_backend.entity.MessageEntity;
import com.example.rentals_backend.repository.MessageRepository;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public ApiMessageResponse createMessage(MessageRequest messageRequest) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setMessage(messageRequest.getMessage());
		messageEntity.setUserId(messageRequest.getUserId());
		messageEntity.setRentalId(messageRequest.getRentalId());
		messageEntity.setCreatedAt(LocalDateTime.now());
		messageEntity.setUpdatedAt(LocalDateTime.now());

		messageRepository.save(messageEntity);
		return new ApiMessageResponse("Message send with success");
	}

}
