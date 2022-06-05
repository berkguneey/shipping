package com.fleetmanagement.shipping.service;

import com.fleetmanagement.shipping.dto.IncorrectSentLogDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;

public interface IncorrectSentLogService {

	/**
	 * This method is used to insert a new incorrect sent log (barcode-delivery point pairs) to the system.
	 * 
	 * @param incorrectSentLogRequest (barcode, delivery point id, message)
	 * @return incorrect sent log
	 */
	IncorrectSentLogDto insert(IncorrectSentLogRequestDto incorrectSentLogRequest);

}
