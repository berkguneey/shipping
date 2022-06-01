package com.fleetmanagement.shipping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleRequestDto {

	private String licensePlate;
	private String model;
	
}
