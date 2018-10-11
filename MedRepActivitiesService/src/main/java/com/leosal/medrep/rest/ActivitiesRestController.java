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
import com.leosal.medrep.entity.Activity;
import com.leosal.medrep.services.ActivitiesService;

@RestController
public class ActivitiesRestController implements GenericCRUD<Activity>{
	@Autowired
	private ActivitiesService activitiesService;
	
	@RequestMapping("/ping")
	public String ping() {
		return "Activities ping succeed!";
	}

	@Override
	@RequestMapping("/find")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public @ResponseBody Activity findById(@RequestParam Long id) {
		return activitiesService.findById(id);
	}

	@Override
	@RequestMapping("/findall")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public @ResponseBody List<Activity> findAll() {
		return activitiesService.findAll();
	}

	@Override
	@RequestMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public @ResponseBody Activity saveOrUpdate(@RequestBody Activity entity) {
		return activitiesService.saveOrUpdate(entity);
	}

	@Override
	@RequestMapping("/remove")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_OPERATOR')  or hasAuthority('ROLE_USER')")
	public void remove(@RequestBody Activity entity) {
		activitiesService.remove(entity);
	}
}
