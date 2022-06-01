package com.fleetmanagement.shipping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPointRequestDto {
	
	private String deliveryPoint;
	private Integer point;

}
