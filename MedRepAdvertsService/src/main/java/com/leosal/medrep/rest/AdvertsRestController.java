package com.leosal.medrep.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leosal.dbutils.GenericCRUD;
import com.leosal.medrep.entity.Advert;
import com.leosal.medrep.services.AdvertsService;

@RestController
public class AdvertsRestController implements GenericCRUD<Advert>{
	
	@Autowired
	private AdvertsService advertService;
	
	@RequestMapping("/ping")
	public String ping() {
		return "Adverts ping succeed!";
	}

	@Override
	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody Advert findById(@RequestParam Long id) {
		return advertService.findById(id);
	}

	@Override
	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody List<Advert> findAll() {
		return advertService.findAll();
	}

	@Override
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public @ResponseBody Advert saveOrUpdate(@RequestBody Advert entity) {
		return advertService.saveOrUpdate(entity);
	}

	@Override
	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')")
	public void remove(@RequestBody Advert entity) {
		advertService.remove(entity);
	}
}
