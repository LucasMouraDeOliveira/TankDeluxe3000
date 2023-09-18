package com.luma.tankdeluxe.controller.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public User getAuthenticatedUser(Principal principal) {
		return this.userService.find(principal.getName());
	}
	
}
