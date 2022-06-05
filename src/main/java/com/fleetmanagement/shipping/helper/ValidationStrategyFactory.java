package com.fleetmanagement.shipping.helper;

public interface ValidationStrategyFactory {
	
	/**
	 * This method returns the appropriate strategy to run validations corresponding to the given delivery point.
	 * 
	 * @param deliveryPointId
	 * @return validation strategy
	 */
	DeliveryPointValidationStrategy getStrategy(Long deliveryPointId);

}
