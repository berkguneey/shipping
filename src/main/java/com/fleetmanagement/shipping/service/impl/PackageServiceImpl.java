package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.constant.PackageStatus;
import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.Bag;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.model.Package;
import com.fleetmanagement.shipping.repository.PackageRepository;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.DeliveryPointService;
import com.fleetmanagement.shipping.service.PackageService;
import com.fleetmanagement.shipping.util.PackageValidation;

@Service
public class PackageServiceImpl implements PackageService {

	private final PackageRepository repository;
	private final DeliveryPointService deliveryPointService;
	private final BagService bagService;
	private final ModelMapper mapper;

	@Autowired
	public PackageServiceImpl(PackageRepository repository, DeliveryPointService deliveryPointService,
			BagService bagService, ModelMapper mapper) {
		this.repository = repository;
		this.deliveryPointService = deliveryPointService;
		this.bagService = bagService;
		this.mapper = mapper;
	}

	@Override
	public List<PackageDto> getAllPackages() {
		return repository.findAll().stream().map(bag -> mapper.map(bag, PackageDto.class)).collect(Collectors.toList());
	}

	@Override
	public PackageDto getPackageByBarcode(String barcode) {
		return mapper.map(repository.findPackageByBarcode(barcode)
				.orElseThrow(() -> new NoDataFoundException(ErrorConstants.PACKAGE_NOT_FOUND)), PackageDto.class);
	}

	@Override
	public PackageDto insert(PackageRequestDto packageRequest) {
		if (repository.existsPackageByBarcode(packageRequest.getBarcode())) {
			throw new BusinessException(ErrorConstants.PACKAGE_ALREADY_EXISTS);
		}
		PackageValidation.isValid(packageRequest.getBarcode());
		DeliveryPointDto deliveryPointDto = deliveryPointService
				.getDeliveryPointById(packageRequest.getDeliveryPointId());
		Package mPackage = mapper.map(packageRequest, Package.class);
		mPackage.setDeliveryPoint(mapper.map(deliveryPointDto, DeliveryPoint.class));
		return mapper.map(repository.save(mPackage), PackageDto.class);
	}

	@Override
	public Long delete(String barcode) {
		return repository.deletePackageByBarcode(barcode);
	}

	@Override
	public PackageDto updateBagId(String barcode, PackageRequestDto packageRequest) {
		Package mPackage = mapper.map(getPackageByBarcode(barcode), Package.class);
		Bag mBag = mapper.map(bagService.getBagByBarcode(packageRequest.getBagBarcode()), Bag.class);
		PackageValidation.haveSameDeliveryPoint(mPackage, mBag);
		mPackage.setBag(mBag);
		mPackage.setState(PackageStatus.LOADED_INTO_BAG.getState());
		return mapper.map(repository.save(mPackage), PackageDto.class);
	}

}
