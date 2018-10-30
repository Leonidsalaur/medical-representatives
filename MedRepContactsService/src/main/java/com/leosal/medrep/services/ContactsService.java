package com.leosal.medrep.services;

import java.util.List;

import com.leosal.medrep.dto.ContactDTO;

public interface ContactsService {
	
	public ContactDTO findById(Integer id);

	public List<ContactDTO> findAll();

	public ContactDTO saveOrUpdate(ContactDTO entity);

	public void remove(ContactDTO entity);
	
	public List<ContactDTO> findAllByUserLogin(String userLogin);

}
