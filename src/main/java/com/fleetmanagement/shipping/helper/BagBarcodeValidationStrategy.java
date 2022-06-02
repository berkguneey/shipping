package com.fleetmanagement.shipping.helper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.fleetmanagement.shipping.exception.IncorrectFormatException;

@Component
@Qualifier("BagBarcodeValidation")
public class BagBarcodeValidationStrategy implements ValidationStrategy {

	@Override
	public void validate(String value) {
		if (ObjectUtils.isEmpty(value) || !value.startsWith("C") || value.length() != 7) {
			throw new IncorrectFormatException("Bag barcode format is incorrect. Barcode is " + value);
		}

	}

}
