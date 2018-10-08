package com.leosal.medrep.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertsRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Ping succeed!";
	}
}
