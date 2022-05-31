package com.fleetmanagement.shipping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.repository.DeliveryPointRepository;

@Service
public class DeliveryPointService {

	private final DeliveryPointRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public DeliveryPointService(DeliveryPointRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<DeliveryPointDto> getAllDeliveryPoints() {
		return repository.findAll().stream().map(deliveryPoint -> mapper.map(deliveryPoint, DeliveryPointDto.class))
				.collect(Collectors.toList());
	}

	public DeliveryPointDto getDeliveryPointByValue(Integer value) {
		return mapper.map(repository.findDeliveryPointByValue(value), DeliveryPointDto.class);
	}

	public DeliveryPointDto insert(DeliveryPointRequestDto deliveryPointRequest) {
		DeliveryPoint model = mapper.map(deliveryPointRequest, DeliveryPoint.class);
		return mapper.map(repository.save(model), DeliveryPointDto.class);
	}

	public String delete(Integer value) {
		return repository.deleteDeliveryPointByValue(value);
	}

}
