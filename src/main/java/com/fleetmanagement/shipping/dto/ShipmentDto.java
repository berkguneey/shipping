package com.fleetmanagement.shipping.dto;

import java.util.List;

import lombok.Data;

@Data
public class ShipmentDto {
	
	private String plate;
	private List<RouteDto> route;

}
