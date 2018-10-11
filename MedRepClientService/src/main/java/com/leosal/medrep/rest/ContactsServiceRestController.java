package com.leosal.medrep.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/contacts")
public class ContactsServiceRestController {
	private static final String CONTEXT_PATH = "http://MedRepContacts/contacts";
	
	@Autowired
	private RestTemplate webTemplate;
	
	@HystrixCommand(fallbackMethod="fall")
	@RequestMapping("/ping")
	public String ping() {
		ResponseEntity<String> entity = webTemplate.getForEntity(CONTEXT_PATH + "/ping", String.class);
		return entity.getBody();
	}
	
	public String fall() {
		return CONTEXT_PATH + " ping failed";
	}
}
