package com.fleetmanagement.shipping.service.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.fleetmanagement.shipping.dto.BagDto;
import com.fleetmanagement.shipping.dto.BagRequestDto;
import com.fleetmanagement.shipping.dto.DeliveryDto;
import com.fleetmanagement.shipping.dto.PackageDto;
import com.fleetmanagement.shipping.dto.PackageRequestDto;
import com.fleetmanagement.shipping.dto.RouteDto;
import com.fleetmanagement.shipping.dto.ShipmentDto;
import com.fleetmanagement.shipping.helper.BranchValidationStrategy;
import com.fleetmanagement.shipping.helper.DistributionCenterValidationStrategy;
import com.fleetmanagement.shipping.helper.TransferCenterValidationStrategy;
import com.fleetmanagement.shipping.helper.ValidationStrategyFactory;
import com.fleetmanagement.shipping.service.BagService;
import com.fleetmanagement.shipping.service.PackageService;
import com.fleetmanagement.shipping.service.VehicleService;
import com.fleetmanagement.shipping.service.impl.ShipmentServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShipmentServiceTest {

	@Mock
	VehicleService vehicleService;
	@Mock
	BagService bagService;
	@Mock
	PackageService packageService;
	@Mock
	ValidationStrategyFactory strategyFactory;
	@Mock
	BranchValidationStrategy branchValidationStrategy;
	@Mock
	DistributionCenterValidationStrategy distributionCenterValidationStrategy;
	@Mock
	TransferCenterValidationStrategy transferCenterValidationStrategy;
	@InjectMocks
	ShipmentServiceImpl service;
	
	ShipmentDto shipment;
	List<DeliveryDto> deliveryList;
	DeliveryDto delivery1;
	DeliveryDto delivery2;
	List<RouteDto> routeList;
	RouteDto route1;
	RouteDto route2;
	RouteDto route3;
	
	@BeforeEach
	public void setUp() {
		delivery1 = new DeliveryDto();
		delivery1.setBarcode("P1111111111");
		
		delivery2 = new DeliveryDto();
		delivery2.setBarcode("C111111");
		
		deliveryList = new ArrayList<>(Arrays.asList(delivery1, delivery2));
		
		route1 = new RouteDto();
		route1.setDeliveryPoint(1L);
		route1.setDeliveries(deliveryList);
		
		route2 = new RouteDto();
		route2.setDeliveryPoint(2L);
		route2.setDeliveries(deliveryList);
		
		route3 = new RouteDto();
		route3.setDeliveryPoint(3L);
		route3.setDeliveries(deliveryList);
		
		routeList = new ArrayList<>(Arrays.asList(route1, route2, route3));
		
		shipment = new ShipmentDto();
		shipment.setPlate("34XX444");
		shipment.setRoute(routeList);
		
	}
	
	@Test
	public void testTransfer() {
		when(bagService.update(anyString(), any(BagRequestDto.class))).thenReturn(new BagDto());
		when(packageService.update(anyString(), any(PackageRequestDto.class))).thenReturn(new PackageDto());
		when(strategyFactory.getStrategy("1")).thenReturn(branchValidationStrategy);
		when(strategyFactory.getStrategy("2")).thenReturn(distributionCenterValidationStrategy);
		when(strategyFactory.getStrategy("3")).thenReturn(transferCenterValidationStrategy);
		when(branchValidationStrategy.unload(any(), any())).thenReturn(deliveryList);
		when(distributionCenterValidationStrategy.unload(any(), any())).thenReturn(deliveryList);
		when(transferCenterValidationStrategy.unload(any(), any())).thenReturn(deliveryList);
		assertNotNull(service.transfer(shipment));
		verify(vehicleService, times(1)).getVehicleByLicensePlate(any());
	}

}
