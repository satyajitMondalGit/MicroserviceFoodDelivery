package com.ltimindtree.restaurantsservice.web.dto;

import java.io.Serializable;

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
public class OrderProcessingStatusDTO implements Serializable{

	private long orderId;
	private String status;
	
}
