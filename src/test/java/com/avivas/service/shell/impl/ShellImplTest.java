package com.avivas.service.shell.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.CommandNotFoundException;
import com.avivas.service.shell.Command;
import com.avivas.service.shell.InvalidNumberOfCommandsCommandException;
import com.avivas.service.shell.InvalidSizeOfCommandsException;
import com.avivas.service.shell.ShellException;
import com.avivas.service.shell.impl.AddCommand;
import com.avivas.service.shell.impl.FindCommand;
import com.avivas.service.shell.impl.ShellImpl;

@RunWith(PowerMockRunner.class)
public class ShellImplTest {
	
	private ShellImpl shellImplService = new ShellImpl();
	
	@Test
	public void givenCommandsWithValidCommandLengthNumberWhenExecuteThenExecuteCommands() throws ShellException, CommandException
	{
		// Given 
		String stringCommands = "3\n command1 name1\n command2 name2\n command3 name3";
		String [] returnValues = new String[] {"R1","R2","R3"};
		Command command1 = Mockito.mock(Command.class);
		Mockito.when(command1.getName()).thenReturn("command1");
		Mockito.when(command1.run(new String[]{"command1","name1"})).thenReturn(returnValues[0]);
		
		Command command2 = Mockito.mock(Command.class);
		Mockito.when(command2.getName()).thenReturn("command2");
		Mockito.when(command2.run(new String[]{"command2","name2"})).thenReturn(returnValues[1]);
		
		Command command3 = Mockito.mock(Command.class);
		Mockito.when(command3.getName()).thenReturn("command3");
		Mockito.when(command3.run(new String[]{"command3","name3"})).thenReturn(returnValues[2]);
		
		Map<String,Command> commands =  new HashMap<>();
		commands.put(command1.getName(),command1);
		commands.put(command2.getName(),command2);
		commands.put(command3.getName(),command3);
		this.shellImplService.setMapCommands(commands);
		
		// When 
		String actual = this.shellImplService.execute(stringCommands);
		
		// Then
		String expected = Arrays.asList(returnValues).stream().map(s -> s).collect(Collectors.joining(""));	
		assertEquals("El metodo execute debe regresar las respuesta de los comandos concatenados", expected  , actual);
	}
	
	@Test(expected = InvalidNumberOfCommandsCommandException.class)
	public void givenCommandsWithInvalidCommandLengthNumberWhenExecuteThenThrowInvalidNumberOfCommandsException() throws ShellException
	{
		// Given 
		String stringCommands = "q\n command1 name1\n command2 name2\n command3 name3";
		
		// When 
		this.shellImplService.execute(stringCommands);
		
		// Then
		assertTrue("El metodo execute debe lanzar una excepcion InvalidNumberOfCommandsException", false);
	}
	
	@Test(expected = InvalidNumberOfCommandsCommandException.class)
	public void givenCommandsWithCommandLengthNumberEqual100001WhenExecuteThenThrowInvalidNumberOfCommandsException() throws ShellException
	{
		// Given 
		String stringCommands = "100001\n command1 name1\n command2 name2\n command3 name3";
		
		// When 
		this.shellImplService.execute(stringCommands);
		
		// Then
		assertTrue("El metodo execute debe lanzar una excepcion InvalidNumberOfCommandsException", false);
	}
	
	@Test(expected = InvalidNumberOfCommandsCommandException.class)
	public void givenCommandsWithCommandLengthNumberEqualToZeroWhenExecuteThenThrowInvalidNumberOfCommandsException() throws ShellException
	{
		// Given 
		String stringCommands = "0\n command1 name1\n command2 name2\n command3 name3";
		
		// When 
		this.shellImplService.execute(stringCommands);
		
		// Then
		assertTrue("El metodo execute debe lanzar una excepcion InvalidNumberOfCommandsException", false);
	}
	
	@Test(expected = InvalidSizeOfCommandsException.class)
	public void givenCommandsWithInValidCommandLengthWhenExecuteThenExecuteCommands() throws ShellException, CommandException
	{
		// Given 
		String stringCommands = "4\n command1 name1\n command2 name2\n command3 name3";
		String [] returnValues = new String[] {"R1","R2","R3"};
		Command command1 = Mockito.mock(Command.class);
		Mockito.when(command1.getName()).thenReturn("command1");
		Mockito.when(command1.run(new String[]{"command1","name1"})).thenReturn(returnValues[0]);
		
		Command command2 = Mockito.mock(Command.class);
		Mockito.when(command2.getName()).thenReturn("command2");
		Mockito.when(command2.run(new String[]{"command2","name2"})).thenReturn(returnValues[1]);
		
		Command command3 = Mockito.mock(Command.class);
		Mockito.when(command3.getName()).thenReturn("command3");
		Mockito.when(command3.run(new String[]{"command3","name3"})).thenReturn(returnValues[2]);
		
		Map<String,Command> commands =  new HashMap<>();
		commands.put(command1.getName(),command1);
		commands.put(command2.getName(),command2);
		commands.put(command3.getName(),command3);
		this.shellImplService.setMapCommands(commands);
		
		// When 
		String actual = this.shellImplService.execute(stringCommands);
		
		// Then
		String expected = Arrays.asList(returnValues).stream().map(s -> s).collect(Collectors.joining("\n")) + "\n";	
		assertEquals("El metodo execute debe regresar las respuesta de los comandos concatenados", expected  , actual);
	}

	@Test
	public void givenEmptyCommandWhenExecuteThenReturnEmptyString() throws ShellException
	{
		// Given 
		String stringCommands = "";
		
		// When 
		String result = this.shellImplService.execute(stringCommands);
		
		// Then
		assertTrue("El metodo execute debe regresar una cadena vacian", result.isEmpty());
	}
	
	@Test
	public void givenCommandsWithEmptyCommandWhenExecuteThenContinueAndExecuteOtherCommands() throws ShellException, CommandException
	{
		// Given 
		String stringCommands = "2\n \n command1 name1\n";
		Command command1 = Mockito.mock(Command.class);
		Mockito.when(command1.getName()).thenReturn("command1");
		Mockito.when(command1.run(new String[]{"command1","name1"})).thenReturn("return-value");
		Map<String,Command> commands =  new HashMap<>();
		commands.put(command1.getName(),command1);
		this.shellImplService.setMapCommands(commands);
		
		// When 
		String result = this.shellImplService.execute(stringCommands);
		
		// Then
		assertEquals("El metodo execute debe regresar una cadena vacian", "return-value",result);
	}
	
	@Test(expected = CommandNotFoundException.class)
	public void givenInvalidCommandWhenExecuteThenThrowCommandNotFoundException() throws ShellException
	{
		// Given 
		String stringCommands = "2\n \n command1 name1\n";
		
		// When 
		this.shellImplService.execute(stringCommands);
		
		// Then
		assertTrue("El metodo execute debe lanzar CommandNotFoundException",false);
	}
	
	@Test
	public void givenACommandFailWhenExecuteThenNotContinueExecuteOtherCommands() throws ShellException, CommandException
	{
		// Given 
		String stringCommands = "2\n command1 name1\n command2 name2";
		
		Command command1 = Mockito.mock(Command.class);
		Mockito.when(command1.getName()).thenReturn("command1");
		Mockito.when(command1.run(new String[]{"command1","name1"})).thenThrow(new CommandException("error"));
		
		Command command2 = Mockito.mock(Command.class);
		Mockito.when(command2.getName()).thenReturn("command2");
		Mockito.when(command2.run(new String[]{"command2","name2"})).thenReturn("algo");
		
		Map<String,Command> commands =  new HashMap<>();
		commands.put(command1.getName(),command1);
		commands.put(command2.getName(),command2);
		this.shellImplService.setMapCommands(commands);
		
		// When 
		this.shellImplService.execute(stringCommands);
		
		// Then
		Mockito.verify(command2, Mockito.never()).run(Mockito.any());
	}
	
	@Test
	public void givenSetAndFindServiceCommandWhenInitThenMapContainSetAndFindService()
	{
		// Given
		List<Command> listCommands = new ArrayList<>();
		listCommands.add(new AddCommand());
		listCommands.add(new FindCommand());
		this.shellImplService.setListCommands(listCommands);
		// When
		this.shellImplService.init();
		// Then
		assertNotNull("El comando add debe estar en el mapa", this.shellImplService.getMapCommands().get("add"));
		assertNotNull("El comando find debe estar en el mapa", this.shellImplService.getMapCommands().get("find"));
	}
}
