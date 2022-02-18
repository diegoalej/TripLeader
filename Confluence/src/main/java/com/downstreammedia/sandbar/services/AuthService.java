package com.downstreammedia.sandbar.services;

import com.downstreammedia.sandbar.entities.User;

/**
 * Interface defines methods for registering new users
 * 
 * @author Diego Hoyos
 */
public interface AuthService {
	
	public User register(User user);

}
