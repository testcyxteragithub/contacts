package com.avivas.service.shell.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.avivas.dao.shell.ContactDao;
import com.avivas.dao.shell.DuplicateContactException;
import com.avivas.service.shell.CommandException;
import com.avivas.service.shell.DuplicateNameCommandException;
import com.avivas.service.shell.InvalidNameCommandException;
import com.avivas.service.shell.InvalidNameLengthCommandException;
import com.avivas.util.StringUtil;

@Component
@RequestScope
class AddCommand extends AbstractCommand {

	@Autowired
	private ContactDao contactDao;
	
	public AddCommand() {
		super("add");
	}

	@Override
	public String run(String [] args) throws CommandException {
		
		if( (args[1].length() < 1) || (args[1].length() > 21))
		{
			throw new InvalidNameLengthCommandException("La longitud del name debe estar entre 1 y 21. Longitud actual:" + args[1].length()); 
		}
		
		if( !StringUtil.isLowerCaseAndAscii(args[1]) )
		{
			throw new InvalidNameCommandException("El nombre es invalido porque contiene mayusculas o letras que no son del ingles. Valor actual:[" + args[1] + "]");
		}
		
		try {
			getContactDao().add(args[1]);
		} catch (DuplicateContactException e) {
			throw new DuplicateNameCommandException("El nombre [" + args[1] + "] ya fue agregado", e);
		}
		return "";
	}
	
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public ContactDao getContactDao() {
		return contactDao;
	}
}
