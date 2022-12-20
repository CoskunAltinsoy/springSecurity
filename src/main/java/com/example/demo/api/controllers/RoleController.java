package com.example.demo.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.RoleService;
import com.example.demo.entities.Role;

@RestController
@RequestMapping(path = "api/roles/")
public class RoleController {

	private final RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("getall")
	public List<Role> getRoles() {
		return this.roleService.getall();
	}
	
	@PostMapping("add")
	public void addRole(@RequestBody Role role) {
		this.roleService.addRole(role);
	}
	
}
