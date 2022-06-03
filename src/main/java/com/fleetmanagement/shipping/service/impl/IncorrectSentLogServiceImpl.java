package com.fleetmanagement.shipping.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.IncorrectSentLogDto;
import com.fleetmanagement.shipping.dto.IncorrectSentLogRequestDto;
import com.fleetmanagement.shipping.model.IncorrectSentLog;
import com.fleetmanagement.shipping.repository.IncorrectSentLogRepository;
import com.fleetmanagement.shipping.service.IncorrectSentLogService;

@Service
public class IncorrectSentLogServiceImpl implements IncorrectSentLogService {

	private final IncorrectSentLogRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public IncorrectSentLogServiceImpl(IncorrectSentLogRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public IncorrectSentLogDto insert(IncorrectSentLogRequestDto incorrectSentLogRequest) {
		IncorrectSentLog mIncorrectSentLog = mapper.map(incorrectSentLogRequest, IncorrectSentLog.class);
		return mapper.map(repository.save(mIncorrectSentLog), IncorrectSentLogDto.class);
	}

}
