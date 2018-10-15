package com.leosal.medrep.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leosal.medrep.entity.Contact;

@Service
public class ContactsServiceImpl implements ContactsService {

	@Override
	public Contact findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact saveOrUpdate(Contact entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Contact entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Contact> findByRepLogin(String login) {
		//TODO
		return null;
	}

}
