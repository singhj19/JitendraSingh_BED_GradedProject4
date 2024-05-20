package com.js.ems.exception;

public class EmployeeServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
