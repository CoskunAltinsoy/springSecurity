package com.example.demo.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "user_id")
	private int id;
	
	@JoinColumn(name = "user_name")
	private String name;
	
	@JoinColumn(name = "user_email")
	private String email;
	
	@JoinColumn(name = "user_date")
	private LocalDate date;
	
	@Transient
	@JoinColumn(name = "user_age")
	private Integer age;
	
	@ManyToMany
	@JoinTable(name = "user_role",
			      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
			      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
	private List<Role> roles;
	
	
	public User() {
		super();
	}

	public User(int id, String name, String email, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.date = date;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getAge() {
		return Period.between(date, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", date=" + date + ", age=" + age + "]";
	}
	
	
}
