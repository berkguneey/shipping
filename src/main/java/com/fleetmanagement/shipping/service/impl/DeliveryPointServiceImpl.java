package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.DeliveryPointRequestDto;
import com.fleetmanagement.shipping.exception.AlreadyExistsException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.repository.DeliveryPointRepository;
import com.fleetmanagement.shipping.service.DeliveryPointService;

@Service
public class DeliveryPointServiceImpl implements DeliveryPointService {

	private final DeliveryPointRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public DeliveryPointServiceImpl(DeliveryPointRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<DeliveryPointDto> getAllDeliveryPoints() {
		return repository.findAll().stream().map(deliveryPoint -> mapper.map(deliveryPoint, DeliveryPointDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public DeliveryPointDto getDeliveryPointById(UUID id) {
		return mapper.map(
				repository.findById(id)
						.orElseThrow(() -> new NoDataFoundException("Delivery point not found. Id is " + id)),
				DeliveryPointDto.class);
	}

	@Override
	public DeliveryPointDto insert(DeliveryPointRequestDto deliveryPointRequest) {
		if (repository.existsDeliveryPointByName(deliveryPointRequest.getName())) {
			throw new AlreadyExistsException(
					"Delivery point  already exists. Name is " + deliveryPointRequest.getName());
		}
		DeliveryPoint model = mapper.map(deliveryPointRequest, DeliveryPoint.class);
		return mapper.map(repository.save(model), DeliveryPointDto.class);
	}

	@Override
	public void delete(UUID id) {
		repository.deleteById(id);
	}

}
