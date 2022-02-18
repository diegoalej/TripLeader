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


@RestController
@CrossOrigin({ "*", "http://localhost:4200" }) // Angular local port
public class AuthController {

	@Autowired
	private AuthService svc;
	
	@Autowired
	private UserService userSvc;
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(
			@RequestBody User user) {
	    if (user == null) {
	        throw new ResourceNotUpdatedException(0, "User could not be created");
	    }
	    user = svc.register(user);
	    return new ResponseEntity<User>(HttpStatus.CREATED);
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public  ResponseEntity<User> authenticate(Principal principal) {
		User user =  userSvc.findUserByName(principal.getName());
		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}
}