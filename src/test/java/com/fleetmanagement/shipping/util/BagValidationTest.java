package com.fleetmanagement.shipping.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fleetmanagement.shipping.exception.BusinessException;

@SpringBootTest
class BagValidationTest {
	
	@Test
	public void testCheckValid() {
		assertThrows(BusinessException.class, () -> BagValidation.checkValid("C1111111"));
		assertThrows(BusinessException.class, () -> BagValidation.checkValid("C11111"));
		assertThrows(BusinessException.class, () -> BagValidation.checkValid("P111111"));
		assertThrows(BusinessException.class, () -> BagValidation.checkValid("1111111"));
		assertThrows(BusinessException.class, () -> BagValidation.checkValid(""));
	}
	
	@Test
	public void testIsValid() {
		assertEquals(true, BagValidation.isValid("C111111"));
		assertEquals(false, BagValidation.isValid("C1111111"));
		assertEquals(false, BagValidation.isValid("C11111"));
		assertEquals(false, BagValidation.isValid("P111111"));
		assertEquals(false, BagValidation.isValid("1111111"));
		assertEquals(false, BagValidation.isValid(""));
	}

}
