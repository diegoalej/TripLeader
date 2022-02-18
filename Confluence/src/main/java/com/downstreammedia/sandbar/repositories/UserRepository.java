package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.User;

/**
 * Interface is JPA repository for User class
 * 
 * @author Diego Hoyos
 */
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);
	
}
