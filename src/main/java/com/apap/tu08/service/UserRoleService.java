package com.apap.tu08.service;

import com.apap.tu08.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser (UserRoleModel user);
	public String encrypt(String password);
	UserRoleModel getUser(String user);
	boolean matchPassword(String rawPassword,String encodePassword);
}
