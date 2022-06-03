package com.fleetmanagement.shipping.service;

import com.fleetmanagement.shipping.dto.IncorrectSentLogDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;

public interface IncorrectSentLogService {

	IncorrectSentLogDto insert(IncorrectSentLogRequestDto incorrectSentLogRequest);

}
