package com.avivas.service.shell;

public class InvalidPartialCommandException extends CommandException {

	private static final long serialVersionUID = 1L;

	public InvalidPartialCommandException(String message) {
		super(message);
	}
}
