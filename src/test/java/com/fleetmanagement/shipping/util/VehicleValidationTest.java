package com.fleetmanagement.shipping.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fleetmanagement.shipping.exception.BusinessException;

@SpringBootTest
class VehicleValidationTest {
	
	@Test
	public void testIsValid() {
		assertThrows(BusinessException.class, () -> VehicleValidation.isValid("34XX44444"));
		assertThrows(BusinessException.class, () -> VehicleValidation.isValid("34XXXX444"));
		assertThrows(BusinessException.class, () -> VehicleValidation.isValid("34XX44"));
		assertThrows(BusinessException.class, () -> VehicleValidation.isValid("344XX444"));
	}

}
