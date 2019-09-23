package com.avivas.service.shell;

public class InvalidNameCommandException extends CommandException {

	private static final long serialVersionUID = 1L;

	public InvalidNameCommandException(String message) {
		super(message);
	}
}
