package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leosal.medrep.dto.SpecialtyDTO;
import com.leosal.medrep.entity.Specialty;
import com.leosal.medrep.services.SpecialtyService;

@RestController
@RequestMapping("/specialties")
public class SpecialtyRestController {
	@Autowired
	private SpecialtyService specialtyService;

	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody SpecialtyDTO findById(@RequestParam Integer id) {
		return specialtyService.findById(id);
	}

	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR') or hasAuthority('ROLE_USER')")
	public @ResponseBody List<SpecialtyDTO> findAll() {
		return specialtyService.findAll();
	}

	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody SpecialtyDTO saveOrUpdate(@RequestBody SpecialtyDTO entity) {
		return specialtyService.saveOrUpdate(entity);
	}

	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public void remove(@RequestBody SpecialtyDTO entity) {
		specialtyService.remove(entity);
	}

}
