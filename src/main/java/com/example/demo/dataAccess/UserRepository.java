package com.example.demo.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "Select s from users u where u.user_email = ?1",nativeQuery = true)
	Optional<User> findStudentByEmail(String email);
}
