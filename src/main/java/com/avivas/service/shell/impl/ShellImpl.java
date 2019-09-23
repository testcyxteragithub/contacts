package com.avivas.service.shell.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.avivas.service.shell.Command;
import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.CommandNotFoundException;
import com.avivas.service.shell.InvalidNumberOfCommandsCommandException;
import com.avivas.service.shell.InvalidSizeOfCommandsException;
import com.avivas.service.shell.Shell;
import com.avivas.service.shell.ShellException;

@Component
@RequestScope
class ShellImpl implements Shell {

	private Map<String, Command> mapCommands = new HashMap<>();
	
	@Autowired
	private List<Command> listCommands;
	
	@PostConstruct
	public void init()
	{
		for(Command command : listCommands )
		{
			mapCommands.put(command.getName(),command);
		}
	}
	
	@Override
	public String execute(String execute) throws ShellException {
		StringBuilder stringBuilder = new StringBuilder();
		
		String trimCommands = execute.trim();
		if(trimCommands.length() == 0)
		{
			return stringBuilder.toString();
		}
		
		String [] commands = trimCommands.split("\n");
		validateNumberOfCommands(commands);
		
		for(int i = 1 ; i < commands.length ; i++ )
		{
			String line = commands[i].trim();
			if(line.length() > 0)
			{
				String [] splitCommand = line.split(" ");
				Command commandService = mapCommands.get(splitCommand[0]);
				if(commandService == null)
				{
					throw new CommandNotFoundException("Comando:[" + splitCommand[0] + "] de la linea:"  + i + " no encontrado");
				}
				
				try
				{
					stringBuilder.append(commandService.run(splitCommand));
				}
				catch(CommandException commandException)
				{
					stringBuilder.append("Error al ejecutar el comando:[");
					stringBuilder.append(splitCommand[0]);
					stringBuilder.append("] Linea:");
					stringBuilder.append(i+1);
					stringBuilder.append(" se regresa el error:");
					stringBuilder.append(commandException.getMessage());
					break;
				}
			}
		}
		return stringBuilder.toString();
	}
	
	void validateNumberOfCommands(String [] commands) throws InvalidNumberOfCommandsCommandException, InvalidSizeOfCommandsException
	{
		int numberOfCommands = 0;
		try
		{
			numberOfCommands = Integer.valueOf(commands[0]);
		}
		catch(NumberFormatException numberFormatException)
		{
			throw new InvalidNumberOfCommandsCommandException("El numero de comandos debe ser un valor entero entre 1 y 100000. Valor actual:[" + commands[0] +"]"); 
		}
		
		if(numberOfCommands < 1 || numberOfCommands > 100_000 )
		{
			throw new InvalidNumberOfCommandsCommandException("El numero de comandos debe ser un valor entero entre 1 y 100000. Valor actual:[" + commands[0] +"]");
		}
		
		if( (numberOfCommands + 1) != commands.length )
		{
			throw new InvalidSizeOfCommandsException("El numero de comandos especificado es distinto a los enviados. Valor indicado:" + numberOfCommands + " Encontrados:" + (commands.length - 1));
		}
	}
	
	public void setMapCommands(Map<String, Command> mapCommands) {
		this.mapCommands = mapCommands;
	}
	
	public Map<String, Command> getMapCommands() {
		return  new HashMap<>(mapCommands);
	}
	
	public void setListCommands(List<Command> listCommands) {
		this.listCommands = listCommands;
	}	
}
