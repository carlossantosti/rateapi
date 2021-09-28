package com.luxoft.hsbc.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class HsbcException extends RuntimeException {

	private static final long serialVersionUID = -358971959294226478L;
	
	public HsbcException(String message) {
		super(message);
	}

}
