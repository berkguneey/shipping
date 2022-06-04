package com.fleetmanagement.shipping.helper;

import java.util.List;

import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;

public interface DeliveryPointValidationStrategy {
	
	default void addLog(String barcode, Long deliveryPointId, String message) {
		IncorrectSentLogRequestDto incorrectSentLogRequest = new IncorrectSentLogRequestDto();
		incorrectSentLogRequest.setBarcode(barcode);
		incorrectSentLogRequest.setDeliveryPointId(deliveryPointId);
		incorrectSentLogRequest.setMessage(message);
		getIncorrectSentLogService().insert(incorrectSentLogRequest);
	}
	
	IncorrectSentLogService getIncorrectSentLogService();
	List<DeliveryDto> unload(List<DeliveryDto> deliveryList, Long deliveryPointId);
}
