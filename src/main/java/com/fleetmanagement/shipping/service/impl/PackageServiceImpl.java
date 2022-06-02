package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.DeliveryPointDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.exception.AlreadyExistsException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.DeliveryPoint;
import com.fleetmanagement.shipping.model.Package;
import com.fleetmanagement.shipping.repository.PackageRepository;
import com.fleetmanagement.shipping.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService {

	private final PackageRepository repository;
	private final DeliveryPointServiceImpl deliveryPointService;
	private final ModelMapper mapper;

	@Autowired
	public PackageServiceImpl(PackageRepository repository,  DeliveryPointServiceImpl deliveryPointService,  ModelMapper mapper) {
		this.repository = repository;
		this.deliveryPointService = deliveryPointService;
		this.mapper = mapper;
	}

	@Override
	public List<PackageDto> getAllPackages() {
		return repository.findAll().stream().map(bag -> mapper.map(bag, PackageDto.class)).collect(Collectors.toList());
	}

	@Override
	public PackageDto getPackageByBarcode(String barcode) {
		return mapper.map(repository.findPackageByBarcode(barcode)
				.orElseThrow(() -> new NoDataFoundException("Package not found. Barcode is " + barcode)), PackageDto.class);
	}

	@Override
	public PackageDto insert(PackageRequestDto packageRequest) {
		if (repository.existsPackageByBarcode(packageRequest.getBarcode())) {
			throw new AlreadyExistsException("Package already exists. Barcode is " + packageRequest.getBarcode());
		}
		DeliveryPointDto deliveryPoint = deliveryPointService.getDeliveryPointById(packageRequest.getDeliveryPointId());
		Package model = mapper.map(packageRequest, Package.class);
		model.setDeliveryPoint(mapper.map(deliveryPoint, DeliveryPoint.class));
		return mapper.map(repository.save(model), PackageDto.class);
	}

	@Override
	public Long delete(String barcode) {
		return repository.deletePackageByBarcode(barcode);
	}

}
