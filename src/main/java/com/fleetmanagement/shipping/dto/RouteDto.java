package com.fleetmanagement.shipping.dto;

import java.util.List;

import lombok.Data;

@Data
public class RouteDto {
	
	private Long deliveryPoint;
	private List<DeliveryDto> deliveries;

}
