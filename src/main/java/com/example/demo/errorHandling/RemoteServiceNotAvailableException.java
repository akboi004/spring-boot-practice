package com.example.demo.errorHandling;

public class RemoteServiceNotAvailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2351397151081986375L;

	public RemoteServiceNotAvailableException(String message) {
		super(message);
	}

	public RemoteServiceNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
