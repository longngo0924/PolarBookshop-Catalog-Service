package com.polarbookshop.catalogservice.exception;

public class BookNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 180655207331640006L;

	public BookNotFoundException(String isbn) {
		super("The book with ISBN " + isbn + " was not found.");
	}
}
