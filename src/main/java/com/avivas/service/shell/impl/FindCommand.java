package com.avivas.service.shell.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.avivas.dao.shell.ContactDao;
import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.InvalidPartialCommandException;
import com.avivas.service.shell.InvalidPartialLengthCommandException;
import com.avivas.util.StringUtil;

@Component
@RequestScope
class FindCommand extends AbstractCommand {

	@Autowired
	private ContactDao contactDao;
	
	public FindCommand() {
		super("find");
	}
	
	@Override
	public String run(String [] args) throws CommandException {
		
		if( (args[1].length() < 1) || (args[1].length() > 21))
		{
			throw new InvalidPartialLengthCommandException("La longitud del partial debe estar entre 1 y 21. Longitud actual:" + args[1].length()); 
		}
		
		if( !StringUtil.isLowerCaseAndAscii(args[1]) )
		{
			throw new InvalidPartialCommandException("El partial es invalido porque contiene mayusculas o letras que no son del ingles. Valor actual:[" + args[1] + "]");
		}
		
		int count = 0;
		for(String contact : getContactDao().getContacts())
		{
			if(contact.startsWith(args[1]))
			{
				count++;
			}
		}
		
		return String.valueOf(count) + "\n";
	}
	
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public ContactDao getContactDao() {
		return contactDao;
	}
}
