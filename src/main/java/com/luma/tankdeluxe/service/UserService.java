package com.luma.tankdeluxe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		userRepository.save(user);
	}

	public User loadUserByUsername(String username)  {
		return userRepository.findByLogin(username);
	}
	
}