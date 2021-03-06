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

import com.leosal.medrep.entity.Institution;
import com.leosal.medrep.services.InstitutionsService;

@RestController
public class InstitutionsRestController{
	@Autowired
	InstitutionsService institutionsService;
	
	@RequestMapping("/ping")
	public String ping() {
		return "Institutions ping success";
	}

	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody Institution findById(@RequestParam Long id) {
		return null;
	}
	
	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public List<Institution> findAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getAuthorities().stream().anyMatch(r -> "ROLE_ADMIN,ROLE_OPERATOR".contains(r.getAuthority()))) {
			return institutionsService.findAll();
		} else {
			String login = auth.getName();
			return institutionsService.findByRepLogin(login);
		}

	}
	
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public @ResponseBody Institution saveOrUpdate(@RequestBody Institution entity) {
		return institutionsService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public void remove(@RequestBody Institution entity) {
		institutionsService.remove(entity);
	}

}
