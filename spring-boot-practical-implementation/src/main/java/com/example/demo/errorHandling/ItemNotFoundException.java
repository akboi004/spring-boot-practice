package com.example.demo.errorHandling;

public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7288243222842374572L;
	private Long id;

	public ItemNotFoundException(Long id) {
		super("Could not find item: " + id);
	}
}
