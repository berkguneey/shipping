package com.fleetmanagement.shipping.dto;

import lombok.Data;

@Data
public class BagRequestDto {
	
	private String barcode;
	private Long deliveryPointId;
	private Integer state;

}
