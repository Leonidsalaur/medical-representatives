package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leosal.medrep.dto.EducTypeDTO;
import com.leosal.medrep.services.EducationService;

@RestController
@RequestMapping("/educations")
public class EducationRestController {
	@Autowired
	private EducationService educationService;
	
	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody EducTypeDTO findById(@RequestParam Integer id) {
		return educationService.findById(id);
	}

	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody List<EducTypeDTO> findAll() {
		return educationService.findAll();
	}

	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody EducTypeDTO saveOrUpdate(@RequestBody EducTypeDTO entity) {
		return educationService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public void remove(@RequestBody EducTypeDTO entity) {
		educationService.remove(entity);
	}

}
