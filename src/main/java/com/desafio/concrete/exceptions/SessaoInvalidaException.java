package com.desafio.concrete.exceptions;

import org.springframework.http.HttpStatus;

public class SessaoInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;

	public SessaoInvalidaException(HttpStatus httpStatus, String msg) {
		super(msg);
		
		status = httpStatus;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
