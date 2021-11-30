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

import com.downstreammedia.sandbar.entities.UserEquipment;
import com.downstreammedia.sandbar.services.UserEquipmentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 4220
public class UserEquipmentController {
	
	@Autowired
	UserEquipmentService ueServ;
	
	@GetMapping("userequipment")
	List<UserEquipment> getAllUserEquipment(HttpServletResponse response){
		List<UserEquipment> userequipment = ueServ.findAllUserEquipment();
		if(userequipment.size() > 0) {
			return userequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("userequipment/creator/{id}")
	List<UserEquipment> findUserEquipmentByCreatorId(@PathVariable Integer id, HttpServletResponse response) {
		List<UserEquipment> userequipment = ueServ.findUserEquipmentByUserId(id);
		if(userequipment.size() > 0) {
			return userequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("userequipment/trip/{id}")
	List<UserEquipment> findUserEquipmentByTripId(@PathVariable Integer id, HttpServletResponse response) {
		List<UserEquipment> userequipment = ueServ.findUserEquipmentByTripId(id);
		if(userequipment.size() > 0) {
			return userequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("userequipment/id/{id}")
	UserEquipment findUserEquipmentById(@PathVariable Integer id, HttpServletResponse response) {
		UserEquipment userequipment = ueServ.findUserEquipmentById(id);
		if(userequipment != null) {
			return userequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@PostMapping("userequipment/trip/{tripId}")
	UserEquipment createUserEquipment(@RequestBody UserEquipment userequipment,
						@PathVariable Integer tripId, 
						HttpServletResponse response,
						Principal principal
						) {
		UserEquipment newUserEquipment = ueServ.createUserEquipment(userequipment, principal.getName(), tripId);
		if(newUserEquipment != null) {
			return newUserEquipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("userequipment/{id}")
	UserEquipment updateUserEquipment(@PathVariable Integer id,
							Principal principal, 
							HttpServletResponse response,
							@RequestBody UserEquipment userequipment) {
		UserEquipment editUserEquipment = ueServ.updateUserEquipment(id, userequipment, principal.getName());
		if(editUserEquipment != null) {
			return editUserEquipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("userequipment/{id}")
	public void deleteUserEquipment(@PathVariable Integer id,
							Principal principal,
							HttpServletResponse response) {
		boolean deleted = false;
		try {
			deleted = ueServ.deleteUserEquipment(id, principal.getName());
			if(deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {
			response.setStatus(404);
		}
	}

}
