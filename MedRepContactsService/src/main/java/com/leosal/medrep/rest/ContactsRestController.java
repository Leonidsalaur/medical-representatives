package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leosal.medrep.entity.Contact;
import com.leosal.medrep.services.ContactsService;

@RestController
public class ContactsRestController{
	@Autowired
	ContactsService contactsService;
	
	@RequestMapping("/ping")
	public String ping() {
		return "Contacts ping success";
	}

	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public @ResponseBody Contact findById(@RequestParam Long id) {
		return contactsService.findById(id);
	}

	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public @ResponseBody List<Contact> findAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getAuthorities().stream().anyMatch(r -> "ROLE_ADMIN,ROLE_OPERATOR".contains(r.getAuthority()))) {
			return contactsService.findAll();
		} else {
			String login = auth.getName();
			return contactsService.findByRepLogin(login);
		}
	}

	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public @ResponseBody Contact saveOrUpdate(@RequestBody Contact entity) {
		return contactsService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')  or hasAuthority('ROLE_USER')")
	public void remove(Contact entity) {
		contactsService.remove(entity);
	}
	
}
