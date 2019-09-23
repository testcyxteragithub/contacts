package com.avivas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avivas.dto.entity.Execution;
import com.avivas.service.ExecutionService;
import com.avivas.service.ShellService;
import com.avivas.service.shell.Shell;
import com.avivas.service.shell.ShellException;

@Service
class ShellImplService implements ShellService 
{
	@Autowired
	private Shell shell;
	@Autowired
	private ExecutionService executionService;
	
	public String execute(String execute) throws ShellException
	{
		Execution execution = new Execution();
		execution.setInput(execute);
		try
		{
			String result = getShell().execute(execute);
			execution.setOutput(result);
			return result;
		}
		catch(ShellException shellException)
		{
			execution.setOutput(shellException.getMessage());
			throw shellException;
		}
		finally {
			getExecutionService().save(execution);
		}
	}
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	public ExecutionService getExecutionService() {
		return executionService;
	}
	
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	
	public Shell getShell() {
		return shell;
	}
}
