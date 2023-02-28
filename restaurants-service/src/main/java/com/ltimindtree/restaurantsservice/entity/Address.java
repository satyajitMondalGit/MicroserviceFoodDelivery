package com.ltimindtree.restaurantsservice.entity;

import javax.persistence.Embeddable;

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
@Embeddable
public class Address {

	private String address1;

	private String city;
	private String State;

	private int PIN;
}
