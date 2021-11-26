package com.downstreammedia.sandbar.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	@Override
	public User findUserById(int id) {
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
	
	@Override
	public User findUserByName(String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			return user;
		}
		else {
			return null;
		}
	}
	
	@Override
	public User createUser(User user) {
		user.setDateStart(LocalDateTime.now());
		user.setDateUpdated(LocalDateTime.now());
		User newUser = userRepo.saveAndFlush(user);
		if(newUser != null) {
			return newUser;
		}
		else {
			return null;
		}
	}
	
	
	//Will need new endpoint and service impl for changing pw
	@Override
	public User updateUser(int id, User user, String username) {
		Optional<User> opt = userRepo.findById(id);
		if(opt.isPresent() && 
				(opt.get().getId() ==  userRepo.findByUsername(username).getId()
				|| userRepo.findByUsername(username).getRole().equals("admin"))
			) {
			User managed = opt.get();
			managed.setUsername(user.getUsername());
			managed.setEmail(user.getEmail());
			managed.setRole(user.getRole());
			managed.setActive(user.isActive());
			managed.setDateUpdated(LocalDateTime.now());
			managed.setImageUrl(user.getImageUrl());
			return userRepo.saveAndFlush(managed);
		}
		return null;
	}
	
	@Override
	public boolean deleteUser(int id, String username) {
		boolean result = false;
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent() && 
				(user.get().getUsername().equals(username)
				|| userRepo.findByUsername(username).getRole().equals("admin"))
		) {
			userRepo.deleteById(id);
			result = true;
		}
		
		return result;
	}
}
