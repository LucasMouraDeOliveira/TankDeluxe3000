package com.isabo.battletank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isabo.battletank.entity.User;
import com.isabo.battletank.service.UserService;

@SpringBootApplication
public class BattleTankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BattleTankApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUp(UserService userService) {
		return args -> createTestPlayer(userService);
	}

	public void createTestPlayer(UserService userService) {
		User user = new User();
		user.setLogin("Kadoc");
		user.setPassword("kadoc");
		userService.createUser(user);
		User user2 = new User();
		user2.setLogin("DonCarlo");
		user2.setPassword("panzani");
		userService.createUser(user2);
		User user3 = new User();
		user3.setLogin("DocteurBellamy");
		user3.setPassword("123456");
		userService.createUser(user3);
	}

}
