package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.exception.AlreadyExistsException;
import com.fleetmanagement.shipping.exception.NoDataFoundException;
import com.fleetmanagement.shipping.model.Vehicle;
import com.fleetmanagement.shipping.repository.VehicleRepository;
import com.fleetmanagement.shipping.service.VehicleService;
import com.fleetmanagement.shipping.util.LicensePlateValidation;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public VehicleServiceImpl(VehicleRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<VehicleDto> getAllVehicles() {
		return repository.findAll().stream().map(vehicle -> mapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VehicleDto getVehicleByLicensePlate(String licensePlate) {
		return mapper.map(
				repository.findVehicleByLicensePlate(licensePlate).orElseThrow(
						() -> new NoDataFoundException("Vehicle not found. License plate is " + licensePlate)),
				VehicleDto.class);
	}

	@Override
	public VehicleDto insert(VehicleRequestDto vehicleRequest) {
		if (repository.existsVehicleByLicensePlate(vehicleRequest.getLicensePlate())) {
			throw new AlreadyExistsException(
					"Vehicle already exists. License plate is " + vehicleRequest.getLicensePlate());
		}
		LicensePlateValidation.validate(vehicleRequest.getLicensePlate());
		Vehicle model = mapper.map(vehicleRequest, Vehicle.class);
		return mapper.map(repository.save(model), VehicleDto.class);
	}

	@Override
	public Long delete(String licensePlate) {
		return repository.deleteVehicleByLicensePlate(licensePlate);
	}

}