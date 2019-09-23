package com.avivas.service.shell.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.avivas.dao.shell.ContactDao;
import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.InvalidPartialCommandException;
import com.avivas.service.shell.InvalidPartialLengthCommandException;
import com.avivas.service.shell.impl.FindCommand;

@RunWith(PowerMockRunner.class)
public class FindCommandTest {

	@Mock
	private ContactDao contactDao;
	@InjectMocks
	private FindCommand findCommandService;

	@Test
	public void givenAddedContactWhenFindThenReturnOne() throws CommandException {
		// Given
		List<String> contacts = new ArrayList<>();
		contacts.add("name");
		Mockito.when(this.contactDao.getContacts()).thenReturn(contacts);
		
		// When
		String actual = this.findCommandService.run(new String[] {"find","name"});
		
		// Then
		assertEquals("El metodo run debe regresar 1", "1\n", actual);
	}
	
	@Test
	public void givenNotAddedContactWhenFindThenReturnOne() throws CommandException {
		// Given
		List<String> contacts = new ArrayList<>();
		contacts.add("jjuyty");
		Mockito.when(this.contactDao.getContacts()).thenReturn(contacts);
		
		// When
		String actual = this.findCommandService.run(new String[] {"find","name"});
		
		// Then
		assertEquals("El metodo run debe regresar 0", "0\n", actual);
	}
	
	@Test(expected = InvalidPartialLengthCommandException.class)
	public void givenContactWithLengthIsZeroWhenFindThenThrowInvalidPartialLengthCommandException() throws CommandException {
		// Given
		String contactName = "";
		// When
		this.findCommandService.run(new String[] {"find",contactName});
	}
	
	@Test(expected = InvalidPartialLengthCommandException.class)
	public void givenContactWithLength22WhenFindThenThrowInvalidPartialLengthCommandException() throws CommandException {
		// Given
		String contactName = "1234567890123456789012";
		// When
		this.findCommandService.run(new String[] {"find",contactName});
	}
	
	@Test(expected = InvalidPartialCommandException.class)
	public void givenContactWithUpperCaseLetterWhenFindThenThrowInvalidPartialCommandException() throws CommandException {
		// Given
		String contactName = "asasL";
		// When
		this.findCommandService.run(new String[] {"find",contactName});
	}
}
