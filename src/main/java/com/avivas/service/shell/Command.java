package com.avivas.service.shell;

public interface Command {

	String getName();
	String run(String [] args) throws CommandException;
}
