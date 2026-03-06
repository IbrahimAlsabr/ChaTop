package com.example.rentals_backend.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Detailed rental response.")
public class RentalDetailResponse {
	@Schema(description = "The unique identifier of the rental.")
	private Long id;

	@Schema(description = "The name of the rental.")
	private String name;

	@Schema(description = "The surface of the rental.")
	private int surface;

	@Schema(description = "The price of the rental.")
	private int price;

	@Schema(description = "Pictures of the rental.")
	private List<String> picture;

	@Schema(description = "The description of the rental.")
	private String description;

	@JsonProperty("owner_id")
	@Schema(description = "The unique identifier of the owner of the rental.")
	private Long ownerId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@Schema(description = "The date when the rental was created.", example = "2012/12/02")
	private LocalDate created_at;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@Schema(description = "The date when the rental was last updated.", example = "2014/12/02")
	private LocalDate updated_at;
}
