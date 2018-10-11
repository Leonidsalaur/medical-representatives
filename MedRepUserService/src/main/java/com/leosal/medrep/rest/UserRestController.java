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

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.MedrepUser;
import com.leosal.medrep.services.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserRestController implements GenericCRUD<MedrepUser>{
	
	@Autowired
	private UserService userService;
	
	@HystrixCommand(fallbackMethod="fall")
	@RequestMapping("/ping")
	public String ping() {
		return "Users ping succeed";
	}
	
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
		System.out.println(user + " saved");
		return userService.saveOrUpdate(user);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public @ResponseBody void remove(@RequestParam MedrepUser user) {
		userService.remove(user);
	}
	
	public String fall() {
		return "Users ping failed";
	}
}
