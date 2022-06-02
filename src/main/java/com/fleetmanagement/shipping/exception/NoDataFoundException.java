package com.fleetmanagement.shipping.exception;

import com.fleetmanagement.shipping.constant.ErrorConstants;

public class NoDataFoundException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3428938007647691358L;

	public NoDataFoundException(ErrorConstants error) {
		super(error);
	}

}
