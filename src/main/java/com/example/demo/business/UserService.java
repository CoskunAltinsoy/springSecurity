package com.example.demo.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dataAccess.UserRepository;
import com.example.demo.entities.User;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUser(){
		return userRepository.findAll();
	}
	
	public void addUser(User user) {
		Optional<User> userOptional = 
				userRepository.findStudentByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		userRepository.save(user);
	}
	
	public void deleteUser(int userId) {
		boolean exist = userRepository.existsById(userId);
		if(!exist) {
			throw new IllegalStateException("user with id" + userId +
					                         "does not exist");
		}
		userRepository.deleteById(userId);
	}
	
	//@Transactional
	public void updateUser(int userId, User user) {
		Optional<User> userOp = userRepository.findById(userId);
		if(!userOp.isPresent()) {
			throw new IllegalStateException("Student with id " +userId + " does not exist");
		}
	
//		if (name != null && !Objects.equals(student.get().getName(), name)) {
//			student.get().setName(name);
//		}
		
		if (user.getEmail() != null && !Objects.equals(userOp.get().getEmail(), user.getEmail())) {
			Optional<User> studentOptional = userRepository.findStudentByEmail(user.getEmail());
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			userOp.get().setName(user.getName());
			userOp.get().setEmail(user.getEmail());
			userOp.get().setDate(user.getDate());
		}
		userRepository.save(userOp.get());
		
		
	}
	
}
