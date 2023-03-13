package com.ltimindtree.paymentservice.web.dto;

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
public class PaymentProcessingDTO {

	
	private cardInformationDTO cardDetails;
	private int amount;
}
