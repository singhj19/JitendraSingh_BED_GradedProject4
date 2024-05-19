package com.js.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.js.ems.entity.Employee;
import com.js.ems.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	User getUserByUsername(String username);	
	
}
