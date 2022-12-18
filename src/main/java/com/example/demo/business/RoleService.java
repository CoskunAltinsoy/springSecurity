package com.example.demo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dataAccess.RoleRepository;
import com.example.demo.entities.Role;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public void addRole(Role role) {
		this.roleRepository.save(role);
	}
	
	public List<Role> getall(){
		return this.roleRepository.findAll();
	}

}
