package com.luma.tankdeluxe.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		this.userRepository.save(user);
	}

	public User find(String username) {
		return userRepository.findByLogin(username);
	}

	public User find(UUID userUuid) {
		return this.userRepository.findByUuid(userUuid);
	}

}