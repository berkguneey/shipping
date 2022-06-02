package com.fleetmanagement.shipping.helper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PackageBarcodeValidationStrategy")
public class PackageBarcodeValidationStrategy implements ValidationStrategy {

	@Override
	public void validate(String value) {
		System.out.print("PackageBarcodeValidationStrategy");
		
	}

}
