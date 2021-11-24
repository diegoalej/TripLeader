package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.User;

public interface UserService {
	
	List<User> findAllUsers();
	
	User findUserById(int id);
	
	User findUserByName(String username);
	
	User createUser(User user);
	
	User updateUser(int id, User user, String username);
	
	boolean deleteUser(int id, String username);

}
