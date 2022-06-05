package com.fleetmanagement.shipping.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fleetmanagement.shipping.constant.BagStatus;
import com.fleetmanagement.shipping.constant.CommonConstants;
import com.fleetmanagement.shipping.constant.ErrorConstants;
import com.fleetmanagement.shipping.constant.PackageStatus;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.dto.RouteDto;
import com.fleetmanagement.shipping.dto.ShipmentDto;
import com.fleetmanagement.shipping.exception.BusinessException;
import com.fleetmanagement.shipping.helper.ValidationStrategyFactory;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.PackageService;
import com.fleetmanagement.shipping.service.ShipmentService;
import com.fleetmanagement.shipping.service.VehicleService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ShipmentServiceImpl implements ShipmentService {

	private final VehicleService vehicleService;
	private final BagService bagService;
	private final PackageService packageService;
	private ValidationStrategyFactory strategyFactory;

	@Autowired
	public ShipmentServiceImpl(VehicleService vehicleService, BagService bagService, PackageService packageService,ValidationStrategyFactory strategyFactory) {
		this.vehicleService = vehicleService;
		this.bagService = bagService;
		this.packageService = packageService;
		this.strategyFactory = strategyFactory;
	}

	@Override
	public ShipmentDto transfer(ShipmentDto shipment) {
		vehicleService.getVehicleByLicensePlate(shipment.getPlate());
		shipment.getRoute().forEach(route -> {
			loadShipments(route.getDeliveries());
			log.info("Route deliveries are loaded to vehicle. Delivery point of route is " + route.getDeliveryPoint());
		});
		unloadShipments(shipment.getRoute());
		return shipment;
	}

	private void loadShipments(List<DeliveryDto> deliveryList) {
		getBagList(deliveryList).forEach(bag -> {
			BagRequestDto bagRequest  = new BagRequestDto();
			bagRequest.setState(BagStatus.LOADED.getState());
			bag.setState(BagStatus.LOADED.getState());
			bagService.update(bag.getBarcode(), bagRequest);
		});
		getPackageList(deliveryList).forEach(pckg -> {
			PackageRequestDto packageRequest  = new PackageRequestDto();
			packageRequest.setState(PackageStatus.LOADED.getState());
			pckg.setState(PackageStatus.LOADED.getState());
			packageService.update(pckg.getBarcode(), packageRequest);
		});
	}
	
	private List<DeliveryDto> getBagList(List<DeliveryDto> deliveryList) {
		return deliveryList.stream()
				.filter(delivery -> delivery.getBarcode().startsWith(CommonConstants.BAG_FIRST_CHR))
				.collect(Collectors.toList());
	}
	
	private List<DeliveryDto> getPackageList(List<DeliveryDto> deliveryList) {
		return deliveryList.stream()
				.filter(delivery -> delivery.getBarcode().startsWith(CommonConstants.PACKAGE_FIRST_CHR))
				.collect(Collectors.toList());
	}
	
	private void unloadShipments(List<RouteDto> routeList) {
		routeList.forEach(route -> {
			Long deliveryPointId = route.getDeliveryPoint();
			try {
				List<DeliveryDto> deliveryList = strategyFactory.getStrategy(deliveryPointId).unload(route.getDeliveries(),route.getDeliveryPoint());
				route.setDeliveries(deliveryList);
				log.info("Route deliveries are unloaded from vehicle. Delivery point of route is " + route.getDeliveryPoint());
			} catch( BusinessException ex) {
				log.error(ErrorConstants.DELIVERY_POINT_INVALID.getMessage() + " Delivery point is " + deliveryPointId);
			}
		});
	}
	
}
