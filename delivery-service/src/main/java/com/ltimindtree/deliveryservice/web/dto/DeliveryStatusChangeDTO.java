package com.ltimindtree.deliveryservice.web.dto;

import java.io.Serializable;

import com.ltimindtree.deliveryservice.web.dto.DeliveryPersonDto.DeliveryPersonDtoBuilder;

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
public class DeliveryStatusChangeDTO implements Serializable {
	private long orderId;
	private long deiveryPerosnId;
	private String status;
}
