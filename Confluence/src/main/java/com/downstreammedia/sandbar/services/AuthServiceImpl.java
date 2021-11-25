package com.downstreammedia.sandbar.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.UserRepository;


@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository repo;

	
	@Override
	public User register(User user) {
		String encoded = encoder.encode(user.getPassword());
		user.setPassword(encoded);
		user.setActive(true);
		user.setRole("user");
		user.setDateStart(LocalDateTime.now());
		user.setDateUpdated(LocalDateTime.now());
		repo.saveAndFlush(user);
		return user;
	}

}
