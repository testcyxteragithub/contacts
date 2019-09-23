package com.avivas.dao.shell;

import java.util.List;

public interface ContactDao {
	void add(String contact) throws DuplicateContactException;
	List<String> getContacts();
}
