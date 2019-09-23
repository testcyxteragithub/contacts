package com.avivas.dao.shell.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.avivas.dao.shell.ContactDao;
import com.avivas.dao.shell.DuplicateContactException;

@Component
@RequestScope
class ContactMemoryDao implements ContactDao {

	private List<String> contacts;
	
	public ContactMemoryDao() {
		setContacts(new ArrayList<>());
	}
	
	@Override
	public void add(String contact) throws DuplicateContactException {
		if(this.contacts.contains(contact))
		{
			throw new DuplicateContactException("Ya existe el elemento:[" + contact + "]");
		}
		this.contacts.add(contact);
	}
	
	@Override
	public List<String> getContacts() {
		return new ArrayList<>(contacts);
	}
		
	public void setContacts(List<String> contacts) {
		this.contacts = new ArrayList<>(contacts);
	}	
}
