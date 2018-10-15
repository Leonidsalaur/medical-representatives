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

import com.leosal.medrep.entity.Region;
import com.leosal.medrep.services.RegionService;

@RestController
public class RegionsRestController {
	@Autowired
	RegionService regionService;
	
	@RequestMapping("/ping")
	public String ping() {
		return "Regions ping succeed!";
	}
	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody Region findById(@RequestParam Long id) {
		return regionService.findById(id);
	}
	
	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody List<Region> findAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.getAuthorities().stream().anyMatch(r -> "ROLE_ADMIN,ROLE_OPERATOR".contains(r.getAuthority()))) {
			return regionService.findAll();
		} else {
			String login = auth.getName();
			return regionService.findByRepLogin(login);
		}
	}

	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody Region saveOrUpdate(@RequestBody Region entity) {
		return regionService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public void remove(@RequestBody Region entity) {
		regionService.remove(entity);
	}
}
