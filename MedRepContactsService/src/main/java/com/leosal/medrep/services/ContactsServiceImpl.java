package com.leosal.medrep.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leosal.medrep.dao.ContactDAO;
import com.leosal.medrep.dto.ContactDTO;
import com.leosal.medrep.entity.Contact;

@Service
public class ContactsServiceImpl implements ContactsService {
	@Autowired
	private ContactDAO contactDAO;
	
	public List<ContactDTO> findAll() {
		List<ContactDTO> result = 
				StreamSupport.stream(contactDAO.findAll().spliterator(), false).map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
		
		return result;
	}

	public ContactDTO saveOrUpdate(ContactDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(ContactDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContactDTO findById(Integer id) {
		Optional<Contact> contact = contactDAO.findById(id);
		
		return contact.isPresent() ? new ContactDTO(contact.get()) : null;
	}

	@Override
	public List<ContactDTO> findAllByUserLogin(String userLogin) {
		List<ContactDTO> result = 
				contactDAO.findByUserLogin(userLogin).stream().map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
		
		return result;
	}
	

}
