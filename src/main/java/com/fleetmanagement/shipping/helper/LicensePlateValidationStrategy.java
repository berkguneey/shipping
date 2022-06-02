package com.fleetmanagement.shipping.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fleetmanagement.shipping.exception.IncorrectFormatException;

@Component
@Qualifier("LicensePlateValidationStrategy")
public class LicensePlateValidationStrategy implements ValidationStrategy {

	@Override
	public void validate(String value) {
		Pattern pattern = Pattern.compile("^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{4,5})|([A-Z]{2})(\\d{3,4})|([A-Z]{3})(\\d{2,3}))$");
		Matcher matcher = pattern.matcher(value);
		if(!matcher.matches()) {
			throw new IncorrectFormatException("License plate format is incorrect. License plate is " + value);
		}
		
	}

}
