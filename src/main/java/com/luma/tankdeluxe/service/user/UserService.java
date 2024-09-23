package com.luma.tankdeluxe.service.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PlayerLevelService playerLevelService;

	@Autowired
	private UserRepository userRepository;

	public User create(String login, String password) {
		User user = User.builder().login(login)
				.password(passwordEncoder.encode(password))
				.role("USER")
				.uuid(UUID.randomUUID())
				.playerLevel(playerLevelService.create())
				.build();

		return this.save(user);
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public User find(String username) {
		return userRepository.findByLogin(username);
	}

	public User find(UUID userUuid) {
		return this.userRepository.findByUuid(userUuid);
	}

}