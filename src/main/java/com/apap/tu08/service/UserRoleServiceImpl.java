package com.apap.tu08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tu08.model.UserRoleModel;
import com.apap.tu08.repository.UserRoleDB;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDB userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel getUser(String user) {
		// TODO Auto-generated method stub
		return userDb.findByUsername(user);
	}

	@Override
	public boolean matchPassword(String rawPassword, String encodePassword) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodePassword);
	}

}
