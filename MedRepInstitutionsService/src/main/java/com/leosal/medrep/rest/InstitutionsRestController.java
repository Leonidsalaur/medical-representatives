package com.leosal.medrep.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstitutionsRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Ping success";
	}

}
