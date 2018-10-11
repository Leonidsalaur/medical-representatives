package com.leosal.medrep.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsRestController {
	
	@RequestMapping("/ping")
	public String ping() {
		return "Products ping succeed!";
	}
}
