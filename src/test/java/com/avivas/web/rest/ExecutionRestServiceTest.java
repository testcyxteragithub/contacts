package com.avivas.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.avivas.service.ExecutionService;

@RunWith(PowerMockRunner.class)
public class ExecutionRestServiceTest {

	@Mock
	private ExecutionService executionService;
	@InjectMocks
	private ExecutionRestService executionRestService;
	private MockMvc mockMvc;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(this.executionRestService);
        builder.setControllerAdvice(new ExecutionRestServiceAdvice());
        this.mockMvc = builder.build();
	}
	
	@Test
	public void whenGetThenReturnOk() throws Exception
	{
		// Given
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/executions");
        
        // When
		ResultActions resultActions = this.mockMvc.perform(requestBuilder);
		
		// Then
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void givenFindAllFailWhenGetThenReturnInternalServerError() throws Exception
	{
		// Given
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/executions");
		Mockito.when(this.executionService.findAll()).thenThrow(new IllegalAccessError("error"));
		        
		// When
		ResultActions resultActions = this.mockMvc.perform(requestBuilder);
				
		// Then
		resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
}
