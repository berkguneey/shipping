package com.fleetmanagement.shipping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fleetmanagement.shipping.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoDataFoundException.class)
	@ResponseBody ErrorResponse handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
		return new ErrorResponse(ex, request);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({IncorrectFormatException.class, AlreadyExistsException.class})
	@ResponseBody ErrorResponse handleIncorrectFormatException(RuntimeException ex, WebRequest request) {
		return new ErrorResponse(ex, request);
	}

}
