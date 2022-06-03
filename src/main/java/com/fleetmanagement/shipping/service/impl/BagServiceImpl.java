package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.repository.BagRepository;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.DeliveryPointService;
import com.fleetmanagement.shipping.util.BagValidation;

@Service
public class BagServiceImpl implements BagService {

	private final BagRepository repository;
	private final DeliveryPointService deliveryPointService;
	private final ModelMapper mapper;

	@Autowired
	public BagServiceImpl(BagRepository repository, DeliveryPointService deliveryPointService, ModelMapper mapper) {
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
				.orElseThrow(() -> new NoDataFoundException(ErrorConstants.BAG_NOT_FOUND)), BagDto.class);
	}

	@Override
	public BagDto insert(BagRequestDto bagRequest) {
		if (repository.existsBagByBarcode(bagRequest.getBarcode())) {
			throw new BusinessException(ErrorConstants.BAG_ALREADY_EXISTS);
		}
		BagValidation.checkValid(bagRequest.getBarcode());
		DeliveryPoint mDeliveryPoint =  mapper.map(deliveryPointService.getDeliveryPointById(bagRequest.getDeliveryPointId()), DeliveryPoint.class);
		Bag mBag = mapper.map(bagRequest, Bag.class);
		mBag.setDeliveryPoint(mDeliveryPoint);
		return mapper.map(repository.save(mBag), BagDto.class);
	}
	
	@Override
	public BagDto update(String barcode, BagRequestDto bagRequest) {
		Bag mBag = mapper.map(getBagByBarcode(barcode), Bag.class);
		if(!ObjectUtils.isEmpty(bagRequest.getDeliveryPointId())) {
			DeliveryPoint mDeliveryPoint = mapper.map(deliveryPointService.getDeliveryPointById(bagRequest.getDeliveryPointId()), DeliveryPoint.class);
			mBag.setDeliveryPoint(mDeliveryPoint);
		}
		mBag.setState(bagRequest.getState());
		return mapper.map(repository.save(mBag), BagDto.class);
	}

	@Override
	public Long delete(String barcode) {
		return repository.deleteBagByBarcode(barcode);
	}

}
