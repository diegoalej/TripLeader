package com.downstreammedia.sandbar.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements UserService and defines business logic
 * for manipulating user entity
 * 
 * @author Diego Hoyos
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	/**
	 * Method returns all users or null
	 * 
	 * @return - a list of all users
	 */
	@Override
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	/**
	 * Method returns user with specific id value
	 * 
	 * @param id - user to be found
	 * @return - a user object
	 */
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
	
	/**
	 * Method returns user with specific username
	 * 
	 * @param username - string of username to be found
	 * @return - a user object or null
	 */
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
	
	/**
	 * Method creates a new user 
	 * 
	 * @param user - user object to be created
	 * @return - created user object or null
	 */
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
	
	
	/**
	 * Method edits existing user 
	 * 
	 * @param id - user to be updated
	 * @param user - edited user object
	 * @param username - user performing the edit
	 * @return - a user object or null
	 */
	@Override
	public User updateUser(int id, User user, String username) {
		Optional<User> opt = userRepo.findById(id);
		if(opt.isPresent() 
				&& (opt.get().getId() ==  userRepo.findByUsername(username).getId()
				|| userRepo.findByUsername(username).getRole().equals("admin"))) {
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
	
	/**
	 * Method deletes user with specific id value
	 * 
	 * @param id - user to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteUser(int id, String username) {
		boolean result = false;
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent() 
				&& (user.get().getUsername().equals(username)
				|| userRepo.findByUsername(username).getRole().equals("admin"))) {
			userRepo.deleteById(id);
			result = true;
		}		
		return result;
	}
}
