package com.avivas.service.shell.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.avivas.dao.shell.ContactDao;
import com.avivas.dao.shell.DuplicateContactException;
import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.DuplicateNameCommandException;
import com.avivas.service.shell.InvalidNameCommandException;
import com.avivas.service.shell.InvalidNameLengthCommandException;
import com.avivas.service.shell.impl.AddCommand;


@RunWith(PowerMockRunner.class)
public class AddCommandTest {

	@Mock
	private ContactDao contactDao; 
	@InjectMocks
	private AddCommand addCommandService;
	
	@Test
	public void givenAValidNameWhenAddNameThenAddName() throws CommandException, DuplicateContactException
	{
		// Given
		String [] args = {"add","nametwo"};
		// When
		this.addCommandService.run(args);
		// Then
		Mockito.verify(this.contactDao).add("nametwo");
	}
	
	@Test(expected = InvalidNameLengthCommandException.class)
	public void givenNameLengthEqual22WhenAddNameThenThrowInvalidLengthNameException() throws CommandException
	{
		// Given
		String [] args = {"add","1234567890123456789012"};
		// When
		this.addCommandService.run(args);
	}
	
	@Test(expected = InvalidNameLengthCommandException.class)
	public void givenNameLengthZeroWhenAddNameThenThrowInvalidLengthNameException() throws CommandException
	{
		// Given
		String [] args = {"add",""};
		// When
		this.addCommandService.run(args);
	}
		
	@Test
	public void givenNameIsAddwhenGetNameThenReturnAdd()
	{
		assertEquals("el metodo getName debe regresar un add","add", this.addCommandService.getName());
	}
	
	@Test(expected = InvalidNameCommandException.class)
	public void givenNameWithUpperCaseLetterWhenAddNameThenThrowInvalidNameException() throws CommandException
	{
		// Given
		String [] args = {"add","Name"};
		// When
		this.addCommandService.run(args);
	}
	
	@Test(expected = DuplicateNameCommandException.class)
	public void givenThereIsNameWhenAddNameThenThrowDuplicateNameCommandException() throws CommandException, DuplicateContactException
	{
		// Given
		String [] args = {"add","name"};
		
		Mockito.doThrow(new DuplicateContactException("error")).when(this.contactDao).add("name");
		// When
		this.addCommandService.run(args);
		this.addCommandService.run(args);
	}
}
