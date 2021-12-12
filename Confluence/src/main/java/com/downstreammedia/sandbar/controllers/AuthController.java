package com.downstreammedia.sandbar.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.User;
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
	public User register(
			@RequestBody User user,
			HttpServletResponse res
		) {
	    if (user == null) {
	        res.setStatus(400);
	    }
	    user = svc.register(user);
	    return user;
	}

	@RequestMapping(path = "/authenticate", method = RequestMethod.GET)
	public User authenticate(Principal principal) {
	    return userSvc.findUserByName(principal.getName());
	}
}