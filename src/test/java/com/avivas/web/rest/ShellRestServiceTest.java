package com.avivas.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.avivas.service.ShellService;
import com.avivas.service.shell.ShellException;

@RunWith(PowerMockRunner.class)
public class ShellRestServiceTest {

	@Mock
	private ShellService shellService;
	@InjectMocks
	private ShellRestService shellRestService;
	private MockMvc mockMvc;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(this.shellRestService);
        builder.setControllerAdvice(new ShellRestServiceAdvice());
        this.mockMvc = builder.build();
	}
	
	@Test
	public void givenValidCommandsWhenPostThenReturnOk() throws Exception
	{
		// Given
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shell");
        requestBuilder.contentType(MediaType.TEXT_PLAIN);
        requestBuilder.content("1\nadd name");
        
        // When
		ResultActions resultActions = this.mockMvc.perform(requestBuilder);
		
		// Then
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void givenInvalidCommandsWhenPostThenReturnBadRequest() throws Exception
	{
		// Given
		String commands = "3\\nadd name";
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shell");
        requestBuilder.contentType(MediaType.TEXT_PLAIN);
        requestBuilder.content(commands);
        Mockito.when(this.shellService.execute(commands)).thenThrow(new ShellException("error"));
        
        // When
		ResultActions resultActions = this.mockMvc.perform(requestBuilder);
		
		// Then
		resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void givenExecuteFailWhenPostThenReturnInternalServerError() throws Exception
	{
		// Given
		String commands = "";
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shell");
        requestBuilder.contentType(MediaType.TEXT_PLAIN);
        requestBuilder.content(commands);
        Mockito.when(this.shellService.execute(commands)).thenThrow(new IllegalAccessError("error"));
        
        // When
		ResultActions resultActions = this.mockMvc.perform(requestBuilder);
		
		// Then
		resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
}
