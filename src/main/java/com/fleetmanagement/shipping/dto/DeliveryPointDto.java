package com.fleetmanagement.shipping.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class DeliveryPointDto {
	
	private UUID id;
	private String deliveryPoint;
	private Integer value;
	private Date createdAt;
	private Date updatedAt;

}
