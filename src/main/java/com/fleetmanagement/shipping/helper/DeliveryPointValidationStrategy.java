package com.fleetmanagement.shipping.helper;

import java.util.List;

import com.fleetmanagement.shipping.dto.DeliveryDto;

public interface DeliveryPointValidationStrategy {
	List<DeliveryDto> unload(List<DeliveryDto> deliveryList, Long deliveryPointId);
}
