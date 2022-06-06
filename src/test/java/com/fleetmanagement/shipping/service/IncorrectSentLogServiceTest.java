package com.fleetmanagement.shipping.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.model.IncorrectSentLog;
import com.fleetmanagement.shipping.repository.IncorrectSentLogRepository;
import com.fleetmanagement.shipping.service.impl.IncorrectSentLogServiceImpl;

@ExtendWith(MockitoExtension.class)
class IncorrectSentLogServiceTest {

	@Mock
	IncorrectSentLogRepository repository;
	@Spy
	ModelMapper mapper = new ModelMapper();
	@InjectMocks
	IncorrectSentLogServiceImpl service;
	
	IncorrectSentLogRequestDto incorrectSentLogRequest;
	IncorrectSentLog incorrectSentLog;;
	
	@BeforeEach
	public void setUp() {
		incorrectSentLogRequest = new IncorrectSentLogRequestDto();
		incorrectSentLogRequest.setBarcode("C725797");
		incorrectSentLogRequest.setDeliveryPointId(1L);
		incorrectSentLogRequest.setMessage("TEST");
		
		incorrectSentLog = new IncorrectSentLog();
		incorrectSentLog.setId(UUID.randomUUID());
		incorrectSentLog.setBarcode("C725797");
		incorrectSentLog.setDeliveryPointId(1L);
		incorrectSentLog.setMessage("TEST");
		incorrectSentLog.setCreatedAt(LocalDateTime.now());
		
	}
	
	@Test
	public void testInsert() {
		when(repository.save(any(IncorrectSentLog.class))).thenReturn(incorrectSentLog);
		assertNotNull(service.insert(incorrectSentLogRequest));
	}

}
