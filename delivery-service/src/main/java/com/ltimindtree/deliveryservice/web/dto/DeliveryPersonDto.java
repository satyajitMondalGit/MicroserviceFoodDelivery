package com.ltimindtree.deliveryservice.web.dto;

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

	private String Name;

	private String email;

	private int mobile;

	private Float rating;
}
