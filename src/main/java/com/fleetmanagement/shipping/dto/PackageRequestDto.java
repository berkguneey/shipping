package com.fleetmanagement.shipping.dto;

import lombok.Data;

@Data
public class PackageRequestDto {
	
	private String barcode;
	private Long deliveryPointId;
	private Integer weight;
	private String bagBarcode;
	private Integer state;

}
