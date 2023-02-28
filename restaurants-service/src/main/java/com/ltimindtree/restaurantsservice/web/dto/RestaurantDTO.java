package com.ltimindtree.restaurantsservice.web.dto;

import com.ltimindtree.restaurantsservice.entity.Address;

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
public class RestaurantDTO {

	private long restaurantId;

	private String restaurantName;

	private Address address;

	private MenuDTO manu;

	private int budget;
}
