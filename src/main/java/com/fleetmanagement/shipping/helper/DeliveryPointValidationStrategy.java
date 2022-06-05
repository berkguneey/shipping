package com.fleetmanagement.shipping.helper;

import java.util.List;

import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;

public interface DeliveryPointValidationStrategy {
	
	/**
	 * This method returns the service object which commonly used by strategies.
	 * 
	 * @return service
	 */
	IncorrectSentLogService getIncorrectSentLogService();
	
	/**
	 * This method unloads the deliveries to the entered delivery point.
	 * 
	 * @param deliveryList (barcode)
	 * @param deliveryPointId
	 * @return delivery list
	 */
	List<DeliveryDto> unload(List<DeliveryDto> deliveryList, Long deliveryPointId);
	
	default void addLog(String barcode, Long deliveryPointId, String message) {
		IncorrectSentLogRequestDto incorrectSentLogRequest = new IncorrectSentLogRequestDto();
		incorrectSentLogRequest.setBarcode(barcode);
		incorrectSentLogRequest.setDeliveryPointId(deliveryPointId);
		incorrectSentLogRequest.setMessage(message);
		getIncorrectSentLogService().insert(incorrectSentLogRequest);
	}
	
}
