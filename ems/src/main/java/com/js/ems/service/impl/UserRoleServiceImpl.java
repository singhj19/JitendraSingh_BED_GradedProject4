package com.js.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.js.ems.entity.Role;
import com.js.ems.entity.User;
import com.js.ems.repository.RoleRepository;
import com.js.ems.repository.UserRepository;
import com.js.ems.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> listUser() {
		return userRepository.findAll();
	}

	@Override
	public void addRole(Role role) {
		roleRepository.save(role);

	}

	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

}
