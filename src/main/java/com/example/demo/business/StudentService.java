package com.example.demo.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dataAccess.StudentRepository;
import com.example.demo.entities.Student;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudent(){
		return studentRepository.findAll();
	}
	
	public void addStudent(Student student) {
		Optional<Student> studentOptional = 
				studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}
	
	public void deleteStudent(int studentId) {
		boolean exist = studentRepository.existsById(studentId);
		if(!exist) {
			throw new IllegalStateException("Student with id" + studentId +
					                         "does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	
	//@Transactional
	public void updateStudent(int studentId, Student student) {
		Optional<Student> studentop = studentRepository.findById(studentId);
		if(!studentop.isPresent()) {
			throw new IllegalStateException("Student with id " +studentId + " does not exist");
		}
	
//		if (name != null && !Objects.equals(student.get().getName(), name)) {
//			student.get().setName(name);
//		}
		
		if (student.getEmail() != null && !Objects.equals(studentop.get().getEmail(), student.getEmail())) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			studentop.get().setName(student.getName());
			studentop.get().setEmail(student.getEmail());
			studentop.get().setDate(student.getDate());
		}
		studentRepository.save(studentop.get());
		
		
	}
	
}
