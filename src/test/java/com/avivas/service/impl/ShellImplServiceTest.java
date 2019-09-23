package com.avivas.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.avivas.service.ExecutionService;
import com.avivas.service.shell.Shell;
import com.avivas.service.shell.ShellException;

@RunWith(PowerMockRunner.class)
public class ShellImplServiceTest {
	@Mock
	private Shell shell;
	@Mock
	private ExecutionService executionService;
	@InjectMocks
	private ShellImplService shellImplService;
	
	@Test
	public void givenCommandsValidWhenExecuteThenExecuteAndSafeExecution() throws ShellException
	{
		// Given
		String execute = "1\n add name\n find na";
		String output = "1";
		Mockito.when(this.shell.execute(execute)).thenReturn(output);
		
		// When
		this.shellImplService.execute(execute);
		
		// Then
		Mockito.verify(this.shell).execute(execute);
		Mockito.verify(this.executionService).save(Mockito.any());
	}
	
	@Test
	public void givenInvalidCommandWhenExecuteThenExecuteAndSafeExecution() throws ShellException
	{
		// Given
		String execute = "1\n add name\n find na";
		Mockito.when(this.shell.execute(execute)).thenThrow(new ShellException(""));
		
		// When
		try
		{
			this.shellImplService.execute(execute);
		}
		catch(ShellException shellException)
		{
			// Then
			Mockito.verify(this.shell).execute(execute);
			Mockito.verify(this.executionService).save(Mockito.any());
		}
	}
}
