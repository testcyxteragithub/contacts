package com.avivas.service.shell.impl;

import com.avivas.service.shell.Command;

public abstract class AbstractCommand implements Command {

	private String name;
	
	public AbstractCommand(String name) {
		setName(name);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
