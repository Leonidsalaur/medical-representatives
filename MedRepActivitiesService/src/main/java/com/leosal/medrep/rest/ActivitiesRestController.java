package com.leosal.medrep.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiesRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Ping succeed!";
	}
}
