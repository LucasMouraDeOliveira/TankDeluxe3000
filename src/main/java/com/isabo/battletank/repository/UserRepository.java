package com.isabo.battletank.repository;

import org.springframework.data.repository.CrudRepository;

import com.isabo.battletank.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String username);

}