package com.apap.tu08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu08.model.UserRoleModel;
import com.apap.tu08.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	private String addUsersubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	//Latihan 2
	@RequestMapping(value="/updatePassword", method=RequestMethod.GET)
	private String update(@ModelAttribute UserRoleModel user) {
		return "update-password";
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	private String updatePasswordSubmit(String oldPassword, String newPassword, String confirmPassword, Model model) {
		UserRoleModel user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		
		if(newPassword.equals(confirmPassword)) {
			
			if(userService.matchPassword(oldPassword, user.getPassword())) {
				user.setPassword(newPassword);
				userService.addUser(user);
				
				model.addAttribute("message", "Password " + user.getUsername() + " berhasil diubah");
				return "update-password-success";
			}
			
			else {
				model.addAttribute("message", "Password lama salah");
				return "update-password";
			}	
		}
		else {
			model.addAttribute("message", "Password tidak sesuai");
			return "update-password";
		}
	}
}
