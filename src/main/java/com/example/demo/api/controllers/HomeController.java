package com.example.demo.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home/")
public class HomeController {

	
	@GetMapping("user")
	public String getUserHello() {
		return "Hello User";
	}
	
	@GetMapping("admin")
	public String getUserAdmin() {
		return "Hello Admin";
	}
}
