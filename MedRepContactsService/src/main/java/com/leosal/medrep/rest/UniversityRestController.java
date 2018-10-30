package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leosal.medrep.dto.UniversityDTO;
import com.leosal.medrep.services.UniversityService;

@RestController
@RequestMapping("/universities")
public class UniversityRestController {
	@Autowired
	private UniversityService universityService;
	
	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody UniversityDTO findById(@RequestParam Integer id) {
		return universityService.findById(id);
	}

	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody List<UniversityDTO> findAll() {
		return universityService.findAll();
	}

	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody UniversityDTO saveOrUpdate(@RequestBody UniversityDTO entity) {
		return universityService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public void remove(@RequestBody UniversityDTO entity) {
		universityService.remove(entity);
	}

}
