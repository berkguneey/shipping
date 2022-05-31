package com.fleetmanagement.shipping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.dto.VehicleDto;
import com.fleetmanagement.shipping.dto.VehicleRequestDto;
import com.fleetmanagement.shipping.model.Vehicle;
import com.fleetmanagement.shipping.repository.VehicleRepository;

@Service
public class VehicleService {

	private final VehicleRepository repository;
	private final ModelMapper mapper;

	@Autowired
	public VehicleService(VehicleRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<VehicleDto> getAllVehicles() {
		return repository.findAll().stream().map(vehicle -> mapper.map(vehicle, VehicleDto.class))
				.collect(Collectors.toList());
	}

	public VehicleDto getVehicleByLicensePlate(String licensePlate) {
		return mapper.map(repository.findVehicleByLicensePlate(licensePlate), VehicleDto.class);
	}

	public VehicleDto insert(VehicleRequestDto vehicleRequest) {
		// tr plaka kontrolü koy.
		Vehicle model = mapper.map(vehicleRequest, Vehicle.class);
		return mapper.map(repository.save(model), VehicleDto.class);
	}

	public String delete(String licensePlate) {
		return repository.deleteVehicleByLicensePlate(licensePlate);
	}

}
