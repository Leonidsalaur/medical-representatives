package com.leosal.medrep.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Reports ping succeed!";
	}
}
