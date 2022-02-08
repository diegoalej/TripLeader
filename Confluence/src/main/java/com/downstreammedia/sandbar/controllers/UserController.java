package com.downstreammedia.sandbar.controllers;
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })//Angular local port 4220
public class UserController {
	
	@Autowired
	private UserService userServ;

	
	@GetMapping("users")
	public ResponseEntity<List<User>> showAllUsers(){
		List<User> users = userServ.findAllUsers();
		if (users.size() > 0) {
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No users found");
		}
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Integer id) {
		User user = userServ.findUserById(id);
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException(id, "User does not exist in database");
		}
	}
	
	
	//Once authentication is added this can be properly restricted to user and admin
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateExistingUser(
			@RequestBody User user, 
			@PathVariable int id, 
			Principal principal
			){
		User userExists = userServ.findUserById(id);
		if(userExists != null) {
			User editUser = userServ.updateUser(id, user, principal.getName() );
			if (editUser != null) {
				return new ResponseEntity<User>(editUser, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "User could not be updated");
			}
		} 
		else {
			throw new ResourceNotFoundException(id, "User does not exist in database");
		}
		
	}
	
	
	//Should delete trips, gear, and expenses
	@DeleteMapping("users/{id}")
	public ResponseEntity<Object> deleteUser(
			@PathVariable Integer id, 
			Principal principal
			){
		boolean result = false;
			result = userServ.deleteUser(id, principal.getName());
			
			if (result == true) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				throw new ResourceNotDeletedException(id, "User could not be deleted");
			}
	}
	

}
