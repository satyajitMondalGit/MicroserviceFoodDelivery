package com.ltimindtree.restaurantsservice.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeliveryPersonDto {

	private long id;

	@NotBlank(message = "Name cannot be null or empty")
	private String Name;

	@Email(message = "invalide email address")
	@NotBlank(message = "Email cannot be null or empty")
	private String email;

	@Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String mobile;

//	@NotBlank(message = "Rating cannot be null or empty")
	private Float rating;
}
