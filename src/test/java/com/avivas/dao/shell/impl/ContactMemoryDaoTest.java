package com.avivas.dao.shell.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.avivas.dao.shell.DuplicateContactException;

public class ContactMemoryDaoTest {

	private ContactMemoryDao contactMemoryDao = new ContactMemoryDao();
	
	@Test
	public void givenContactWhenAddThenAddContact() throws DuplicateContactException
	{
		String contact = "david";
		this.contactMemoryDao.add(contact);
		assertTrue("El dao debe contener el contacto", this.contactMemoryDao.getContacts().contains(contact));
	}
	
	@Test(expected = DuplicateContactException.class)
	public void givenContactWhenAddSameContactTwoTimesThenThrowDuplicateContactException() throws DuplicateContactException
	{
		String contact = "david";
		this.contactMemoryDao.add(contact);
		this.contactMemoryDao.add(contact);
	}
}
