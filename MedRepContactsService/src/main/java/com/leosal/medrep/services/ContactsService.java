package com.leosal.medrep.services;

import java.util.List;

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.Contact;

public interface ContactsService extends GenericCRUD<Contact> {

	List<Contact> findByRepLogin(String login);

}
