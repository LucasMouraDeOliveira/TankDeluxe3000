package com.isabo.battletank.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isabo.battletank.entity.User;
import com.isabo.battletank.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = this.userRepository.findByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		} 
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER_ROLE"));
		
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
	}

}
