package com.luma.tankdeluxe.service;

import java.util.UUID;

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

	public User createUser(String login, String password) {
		var user = new User();
		user.setLogin(login);
		user.setUuid(UUID.randomUUID());
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("USER");
		return userRepository.save(user);
	}

	public User find(String username) {
		return userRepository.findByLogin(username);
	}

	public User find(UUID userUuid) {
		return this.userRepository.findByUuid(userUuid);
	}

}