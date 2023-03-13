package com.ltimindtree.orderservice.web.dto;

import java.io.Serializable;

import com.ltimindtree.orderservice.entity.Order;
import com.ltimindtree.orderservice.web.dto.FoodItemDTO.FoodItemDTOBuilder;

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
public class ResponseOrderDTO implements Serializable {

	private String message;
	
	private Order order;
}
