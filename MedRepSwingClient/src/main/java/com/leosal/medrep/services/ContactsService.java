package com.leosal.medrep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.leosal.medrepear.dto.ContactDTO;

@Service
public class ContactsService {
	@Value("${medrep.contacts.findall.url}")
	private String findAllURL;
	
	@Autowired
	private UserService userService;
	
	public List<ContactDTO> findAll() {
		ResponseEntity<List> response = userService.getRestTemplate().getForEntity(findAllURL, List.class);
		
		if(HttpStatus.OK.equals(response.getStatusCode())) {
			return response.getBody();
		} else {
			throw new HttpClientErrorException(response.getStatusCode(), response.toString());
		}
	}

}
