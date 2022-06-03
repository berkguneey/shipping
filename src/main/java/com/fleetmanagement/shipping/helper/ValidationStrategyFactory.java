package com.fleetmanagement.shipping.helper;

public interface ValidationStrategyFactory {
	
	DeliveryPointValidationStrategy getStrategy(String deliveryPointId);

}
