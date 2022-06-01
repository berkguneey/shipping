package com.fleetmanagement.shipping.service;

import java.util.List;
import java.util.UUID;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;

public interface DeliveryPointService {

	List<DeliveryPointDto> getAllDeliveryPoints();

	DeliveryPointDto getDeliveryPointById(UUID id);

	DeliveryPointDto insert(DeliveryPointRequestDto deliveryPointRequest);

	void delete(UUID id);

}
