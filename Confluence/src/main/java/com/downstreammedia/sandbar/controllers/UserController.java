package com.downstreammedia.sandbar.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.User;
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

}
