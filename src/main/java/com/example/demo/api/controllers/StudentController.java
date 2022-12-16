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

import com.example.demo.business.StudentService;
import com.example.demo.entities.Student;

@RestController
@RequestMapping(path = "api/students/")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("getall")
	public List<Student> getStudent(){
		return studentService.getStudent();		
	}
	
	@PostMapping("add")
	public void registerStudent(@RequestBody Student student) {
		studentService.addStudent(student);
	}
	
	@DeleteMapping(path = "delete/{studentId}")
	public void deleteStudent(@PathVariable("studentId")int studentId) {
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping("update/{studentId}")
	public void updateStudent(@PathVariable("studentId") int studentId,
			                   @RequestBody Student student) {
		studentService.updateStudent(studentId, student);
	}
}
