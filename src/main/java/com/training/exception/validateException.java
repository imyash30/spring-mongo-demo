package com.training.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class validateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HttpStatus status;
	private final String message;
    private final List<String> errors;
	
	public validateException(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public List<String> getErrors() {
		return errors;
	}
	
    
    

}
