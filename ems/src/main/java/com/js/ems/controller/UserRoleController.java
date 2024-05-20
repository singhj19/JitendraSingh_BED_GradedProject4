package com.js.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.ems.entity.Role;
import com.js.ems.entity.User;
import com.js.ems.service.impl.UserRoleServiceImpl;

@RestController
public class UserRoleController {

	@Autowired
	UserRoleServiceImpl userRoleService;

	@GetMapping("/user/list")
	public List<User> listUser() {
		return userRoleService.listUser();
	}

	@PostMapping("/user/add")
	public List<User> addUser(@RequestBody User user) {
		userRoleService.addUser(user);
		return userRoleService.listUser();
	}

	@GetMapping("/role/list")
	public List<Role> listRole() {
		return userRoleService.listRole();
	}

	@PostMapping("/role/add")
	public List<Role> addRole(@RequestBody Role role) {
		userRoleService.addRole(role);
		return userRoleService.listRole();
	}

}
