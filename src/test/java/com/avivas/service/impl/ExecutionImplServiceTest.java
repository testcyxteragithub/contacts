package com.avivas.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.avivas.dao.jpa.ExecutionDao;
import com.avivas.dto.entity.Execution;

@RunWith(PowerMockRunner.class)
public class ExecutionImplServiceTest {
	
	@Mock
	private ExecutionDao executionDao; 
	@InjectMocks
	private ExecutionImplService executionImplService;
	
	@Test
	public void givenExecutionWhenSaveThenSaveExecution()
	{
		// Given
		Execution execution = new Execution();
		execution.setInput("input");
		execution.setOutput("output");
				
		// When
		this.executionImplService.save(execution);
		
		// Then
		Mockito.verify(this.executionDao).save(execution);
	}
	
	@Test
	public void whenFindAllThenFindAll()
	{
		// When
		this.executionImplService.findAll();
		
		// Then
		Mockito.verify(this.executionDao).findAll();
	}
}
