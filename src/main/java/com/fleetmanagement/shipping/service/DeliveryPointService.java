package com.fleetmanagement.shipping.service;

import java.util.List;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;

public interface DeliveryPointService {

	List<DeliveryPointDto> getAllDeliveryPoints();

	DeliveryPointDto getDeliveryPointById(Long id);

	DeliveryPointDto insert(DeliveryPointRequestDto deliveryPointRequest);

	void delete(Long id);

}
