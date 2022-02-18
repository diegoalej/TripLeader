package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

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

import com.downstreammedia.sandbar.entities.UserEquipment;
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.UserEquipmentService;

/**
 * Class is REST controller for User Equipment
 * 
 * @author Diego Hoyos
 */
@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 4220
public class UserEquipmentController {
	
	@Autowired
	UserEquipmentService ueServ;
	
	/**
	 * Endpoint returns all of a users equipment or null
	 * 
	 * @return - a list of all UserEquipment
	 */
	@GetMapping("userequipment")
	ResponseEntity<List<UserEquipment>> getAllUserEquipment(){
		List<UserEquipment> userequipment = ueServ.findAllUserEquipment();
		if(userequipment.size() > 0) {
			return new ResponseEntity<List<UserEquipment>>(userequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No User Equipment found");
		}
	}
	
	/**
	 * Endpoint returns UserEquipment with specific user id value
	 * 
	 * @param id - user id 
	 * @return - a list of equipment or null
	 */
	@GetMapping("userequipment/creator/{id}")
	ResponseEntity<List<UserEquipment>> findUserEquipmentByCreatorId(@PathVariable Integer id) {
		List<UserEquipment> userequipment = ueServ.findUserEquipmentByUserId(id);
		if(userequipment.size() > 0) {
			return new ResponseEntity<List<UserEquipment>>(userequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "No User Equipment found with that user id");
		}
	}
	
	/**
	 * Endpoint returns UserEquipment with specific trip id value
	 * 
	 * @param id - trip id 
	 * @return - a list of equipment or null
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("userequipment/trip/{id}")
	ResponseEntity<List<UserEquipment>> findUserEquipmentByTripId(@PathVariable Integer id) {
		List<UserEquipment> userequipment = ueServ.findUserEquipmentByTripId(id);
		if(userequipment.size() > 0) {
			return new ResponseEntity<List<UserEquipment>>(userequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No User Equipment found with that trip id");
		}
	}
	
	/**
	 * Method returns UserEquipment with specific id value
	 * 
	 * @param id - UserEquipment to be found
	 * @return - a UserEquipment object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("userequipment/id/{id}")
	ResponseEntity<UserEquipment> findUserEquipmentById(@PathVariable Integer id) {
		UserEquipment userequipment = ueServ.findUserEquipmentById(id);
		if(userequipment != null) {
			return new ResponseEntity<UserEquipment>(userequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "No Trip Equipment found with that id");
		}
	}
	
	/**
	 * Method creates a new UserEquipment 
	 * 
	 * @param userequipment - UserEquipment object to be created
	 * @param principal - creator of UserEquipment
	 * @param tripId - trip id to create equipment under
	 * @return - created UserEquipment object or null
	 * @throws - ResourceNotUpdatedException
	 */
	@PostMapping("userequipment/trip/{tripId}")
	ResponseEntity<UserEquipment> createUserEquipment(@RequestBody UserEquipment userequipment,
						@PathVariable Integer tripId, 
						Principal principal) {
		UserEquipment newUserEquipment = ueServ.createUserEquipment(userequipment, principal.getName(), tripId);
		if(newUserEquipment != null) {
			return new ResponseEntity<UserEquipment>(newUserEquipment, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotUpdatedException(0, "User Equipment could not be created");
		}
	}
	
	/**
	 * Endpoint edits existing UserEquipment 
	 * 
	 * @param id - UserEquipment to be updated
	 * @param equipment - edited UserEquipment object
	 * @param username - user performing the edit
	 * @return - a user object or null
	 * @throws - ResourceNotUpdatedException and ResourceNotFoundException
	 */
	@PutMapping("userequipment/{id}")
	ResponseEntity<UserEquipment> updateUserEquipment(@PathVariable Integer id,
							Principal principal, 
							@RequestBody UserEquipment userequipment) {
		UserEquipment ueExists = ueServ.findUserEquipmentById(id);
		if(ueExists != null) {
			UserEquipment editUserEquipment = ueServ.updateUserEquipment(id, userequipment, principal.getName());
			if(editUserEquipment != null) {
				return new ResponseEntity<UserEquipment>(editUserEquipment, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "User Equipment could not be updated");
			}			
		}
		throw new ResourceNotFoundException(id, "User Equipment does not exist in db");
	}
	
	/**
	 * Endpoint deletes UserEquipment with specific id value
	 * 
	 * @param id - UserEquipment to be deleted
	 * @param principal - user performing the delete
	 * @return - boolean with result
	 * @throws - ResourceNotDeletedException
	 */
	@DeleteMapping("userequipment/{id}")
	public ResponseEntity<Object> deleteUserEquipment(@PathVariable Integer id,Principal principal) {
		boolean deleted = false;
			deleted = ueServ.deleteUserEquipment(id, principal.getName());
			if(deleted == true) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			throw new ResourceNotDeletedException(id, "User Equipment could not be deleted");
	}

}
