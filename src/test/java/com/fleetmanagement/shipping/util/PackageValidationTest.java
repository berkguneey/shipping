package com.fleetmanagement.shipping.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fleetmanagement.shipping.exception.BusinessException;

@SpringBootTest
class PackageValidationTest {
	
	@Test
	public void testCheckValid() {
		assertThrows(BusinessException.class, () -> PackageValidation.checkValid("P11111111111"));
		assertThrows(BusinessException.class, () -> PackageValidation.checkValid("P111111111"));
		assertThrows(BusinessException.class, () -> PackageValidation.checkValid("C1111111111"));
		assertThrows(BusinessException.class, () -> PackageValidation.checkValid("1111111111"));
		assertThrows(BusinessException.class, () -> PackageValidation.checkValid(""));
	}
	
	@Test
	public void testIsValid() {
		assertEquals(true, PackageValidation.isValid("P1111111111"));
		assertEquals(false, PackageValidation.isValid("P11111111111"));
		assertEquals(false, PackageValidation.isValid("P111111111"));
		assertEquals(false, PackageValidation.isValid("C1111111111"));
		assertEquals(false, PackageValidation.isValid("1111111111"));
		assertEquals(false, PackageValidation.isValid(""));
	}

}
