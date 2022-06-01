package com.fleetmanagement.shipping.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class BagRequestDto {
	
	private String barcode;
	private UUID deliveryPointId;

}
