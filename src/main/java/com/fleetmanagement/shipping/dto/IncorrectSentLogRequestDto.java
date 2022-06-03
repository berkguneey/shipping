package com.fleetmanagement.shipping.dto;

import lombok.Data;

@Data
public class IncorrectSentLogRequestDto {
	
	private String barcode;
	private Long deliveryPointId;
	private String message;

}
