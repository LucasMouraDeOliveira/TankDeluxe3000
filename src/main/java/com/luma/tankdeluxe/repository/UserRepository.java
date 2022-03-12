package com.luma.tankdeluxe.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.luma.tankdeluxe.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String username);
	
	public User findByUuid(UUID uuid);

}