package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.AuthService;
import com.downstreammedia.sandbar.services.UserService;

/**
 * Class is REST controller for Auth 
 * 
 * @author Diego Hoyos
 */
@RestController
@CrossOrigin({ "*", "http://localhost:4200" }) // Angular local port
public class AuthController {

	@Autowired
	private AuthService svc;
	
	@Autowired
	private UserService userSvc;
	
	/**
	 * Endpoint registers and creates new users
	 * 
	 * @param user - user object to be created
	 * @return - Created user object
	 * @throws - ResourceNotUpdatedException
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<User> register(
			@RequestBody User user) {
	    if (user == null) {
	        throw new ResourceNotUpdatedException(0, "User could not be created");
	    }
	    user = svc.register(user);
	    return new ResponseEntity<User>(HttpStatus.CREATED);
	}
	
	/**
	 * Endpoint authenticates users
	 * 
	 * @param principal - user credentials
	 * @return - Approved status
	 */
	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public  ResponseEntity<User> authenticate(Principal principal) {
		User user =  userSvc.findUserByName(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}