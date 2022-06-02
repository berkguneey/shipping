package com.fleetmanagement.shipping.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class PackageRequestDto {
	
	private String barcode;
	private UUID deliveryPointId;
	private Integer weight;

}
