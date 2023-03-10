package com.ltimindtree.orderservice.web.dto;

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
public class cardInformationDTO implements Serializable{

	private String cardNumber;
	private String nameOfCardHolder;
	private String expDate;
	private String CVV;
}
