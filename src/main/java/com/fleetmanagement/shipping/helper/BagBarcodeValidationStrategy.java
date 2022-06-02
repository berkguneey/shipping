package com.fleetmanagement.shipping.helper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("BagBarcodeValidationStrategy")
public class BagBarcodeValidationStrategy implements ValidationStrategy {

	@Override
	public void validate(String value) {
		System.out.print("BagBarcodeValidationStrategy");
		
	}

}
