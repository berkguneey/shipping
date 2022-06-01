package com.fleetmanagement.shipping.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPointDto {
	
	private UUID id;
	private String deliveryPoint;
	private Integer point;
	private Date createdAt;
	private Date updatedAt;

}
