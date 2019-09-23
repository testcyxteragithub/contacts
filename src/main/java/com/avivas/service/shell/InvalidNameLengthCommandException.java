package com.avivas.service.shell;

public class InvalidNameLengthCommandException extends CommandException {

	private static final long serialVersionUID = 1L;

	public InvalidNameLengthCommandException(String message) {
		super(message);
	}
}
