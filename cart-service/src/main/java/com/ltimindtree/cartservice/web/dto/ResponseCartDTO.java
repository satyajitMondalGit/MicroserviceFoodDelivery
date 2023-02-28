package com.ltimindtree.cartservice.web.dto;

import javax.persistence.Embedded;

import com.ltimindtree.cartservice.entity.FoodItem;

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
public class ResponseCartDTO {

	private long userId;

	private long restaurantId;

	
	private FoodItem fooditem;

	private int totalPrice;
}
