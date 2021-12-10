package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.services.TripService;
import com.downstreammedia.sandbar.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class UserController {
	
	@Autowired
	private UserService userServ;

	
	@GetMapping("users")
	public List<User> showAllUsers(HttpServletResponse resp){
		List<User> users = userServ.findAllUsers();
		if (users.size() > 0) {
			return users;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("users/{id}")
	public User findUserById(@PathVariable Integer id, HttpServletResponse resp) {
		User user = userServ.findUserById(id);
		if(user != null) {
			return user;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	
	
	//Unnecessary?
	@PostMapping("users")
	public User createNewUser(@RequestBody User user, HttpServletResponse resp){
		User newUser = userServ.createUser(user);
		if (newUser != null) {
			return newUser;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	//Once authentication is added this can be properly restricted to user and admin
	@PutMapping("users/{id}")
	public User updateExistingUser(
			@RequestBody User user, 
			@PathVariable int id, 
			HttpServletResponse resp,
			Principal principal
			){
		User editUser = userServ.updateUser(id, user, principal.getName() );//principal.getName()
		if (editUser != null) {
			return editUser;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	
	//Should delete trips, expenses, and meals
	@DeleteMapping("users/{id}")
	public void deleteUser(
			@PathVariable Integer id, 
			HttpServletResponse resp,
			Principal principal
			){
		boolean result = false;
		try {
			result = userServ.deleteUser(id, principal.getName());
			if (result == true) {
				resp.setStatus(204);
			}
		} catch (Exception e) {			
			resp.setStatus(404);
		}
	}
	

}
