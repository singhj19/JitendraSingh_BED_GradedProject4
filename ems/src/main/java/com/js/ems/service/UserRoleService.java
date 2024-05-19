package com.js.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.ems.entity.Role;
import com.js.ems.entity.User;
import com.js.ems.repository.RoleRepository;
import com.js.ems.repository.UserRepository;

@Service
public class UserRoleService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public void addUser(User user) {
		userRepository.save(user);		
	}

	public List<User> listUser() {		
		return userRepository.findAll();
	}

	public void addRole(Role role) {
		roleRepository.save(role);
		
	}

	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
