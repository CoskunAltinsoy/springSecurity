package com.example.demo.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "Select s from student s where s.email = ?1",nativeQuery = true)
	Optional<Student> findStudentByEmail(String email);
}
