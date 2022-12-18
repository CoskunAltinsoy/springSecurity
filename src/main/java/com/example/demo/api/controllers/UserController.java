package com.example.demo.api.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.UserService;
import com.example.demo.entities.User;

@RestController
@RequestMapping(path = "api/user/")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("getall")
	public List<User> getUser(){
		return userService.getUser();		
	}
	
	@PostMapping("add")
	public void registerUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@DeleteMapping(path = "delete/{userId}")
	public void deleteUser(@PathVariable("userId")int userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping("update/{userId}")
	public void updateUser(@PathVariable("userId") int userId,
			                   @RequestBody User user) {
		userService.updateUser(userId, user);
	}
}
