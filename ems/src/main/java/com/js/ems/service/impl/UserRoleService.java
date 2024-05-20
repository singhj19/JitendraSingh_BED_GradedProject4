package com.js.ems.service.impl;

import java.util.List;

import com.js.ems.entity.Role;
import com.js.ems.entity.User;

public interface UserRoleService {

	void addUser(User user);

	List<User> listUser();

	void addRole(Role role);

	List<Role> listRole();

}