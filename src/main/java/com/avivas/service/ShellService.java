package com.avivas.service;

import com.avivas.service.shell.ShellException;

public interface ShellService {
	String execute(String execute) throws ShellException;
}
