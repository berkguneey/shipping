package com.fleetmanagement.shipping.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class VehicleDto {
	
	private UUID id;
	private String licensePlate;
	private String model;
	private Date createdAt;
	private Date updatedAt;

}
