package com.example.rentals_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Test", description = "Test API")
public class TestController {
	@Operation(summary = "Test API", description = "Test API")
	@GetMapping("/test")
	public String test() {
		return "Hello World";
	}
}
