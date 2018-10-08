package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.leosal.medrep.entity.MedrepUser;
import com.leosal.medrep.services.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate webTemplate;
	
	@RequestMapping("/current")
	@PreAuthorize("#oauth2.hasScope('data_read') and hasAuthority('ROLE_ADMIN')")
	public String getCurrentUser() {
		//ResponseEntity<String> greeting = webTemplate.getForEntity("http://MedrepTest/tests/greeting", String.class);
		return "This is current user: " ;//+ greeting.getBody();
	}
	
	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public @ResponseBody List<MedrepUser> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("/byid")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public @ResponseBody MedrepUser findById(@RequestParam Long userId) {
		return userService.findById(userId);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public @ResponseBody MedrepUser saveOrUpdate(@RequestBody MedrepUser user) {
		return userService.saveOrUpdate(user);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public @ResponseBody boolean removeUser(@RequestParam Long userId) {
		return userService.remove(userId);
	}
}
