package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.exception.AlreadyExistsException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.repository.BagRepository;
import com.fleetmanagement.shipping.service.BagService;

@Service
public class BagServiceImpl implements BagService {

	private final BagRepository repository;
	private final DeliveryPointServiceImpl deliveryPointService;
	private final ModelMapper mapper;

	@Autowired
	public BagServiceImpl(BagRepository repository,  DeliveryPointServiceImpl deliveryPointService,  ModelMapper mapper) {
		this.repository = repository;
		this.deliveryPointService = deliveryPointService;
		this.mapper = mapper;
	}

	@Override
	public List<BagDto> getAllBags() {
		return repository.findAll().stream().map(bag -> mapper.map(bag, BagDto.class)).collect(Collectors.toList());
	}

	@Override
	public BagDto getBagByBarcode(String barcode) {
		return mapper.map(repository.findBagByBarcode(barcode)
				.orElseThrow(() -> new NoDataFoundException("Bag not found. Barcode is " + barcode)), BagDto.class);
	}

	@Override
	public BagDto insert(BagRequestDto bagRequest) {
		if (repository.existsBagByBarcode(bagRequest.getBarcode())) {
			throw new AlreadyExistsException("Bag already exists. Barcode is " + bagRequest.getBarcode());
		}
		DeliveryPointDto deliveryPoint = deliveryPointService.getDeliveryPointById(bagRequest.getDeliveryPointId());
		Bag model = mapper.map(bagRequest, Bag.class);
		model.setDeliveryPoint(mapper.map(deliveryPoint, DeliveryPoint.class));
		return mapper.map(repository.save(model), BagDto.class);
	}

	@Override
	public Long delete(String barcode) {
		return repository.deleteBagByBarcode(barcode);
	}

}
