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
public class AddressDTO implements Serializable{

	
	private long addressId;
	private String type;
	private String address1;
	private String city;
	private String State;

	private int PIN;
	private String landMark;
	
}
