package com.downstreammedia.sandbar.controllers;

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

import com.downstreammedia.sandbar.entities.TripEquipment;
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.TripEquipmentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 4220
public class TripEquipmentController {
	
	@Autowired
	TripEquipmentService teServ;
	
	@GetMapping("tripequipment")
	ResponseEntity<List<TripEquipment>> getAllTripEquipment(){
		List<TripEquipment> tripequipment = teServ.findAllTripEquipment();
		if(tripequipment.size() > 0) {
			return new ResponseEntity<List<TripEquipment>>(tripequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No Trip Equipment found");
		}
	}
	

	@GetMapping("tripequipment/trip/{id}")
	ResponseEntity<List<TripEquipment>> findTripEquipmentByTripId(@PathVariable Integer id) {
		List<TripEquipment> tripequipment = teServ.findTripEquipmentByTripId(id);
		if(tripequipment.size() > 0) {
			return new ResponseEntity<List<TripEquipment>>(tripequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "No Trip Equipment found for trip with that id");
		}
	}

	@GetMapping("tripequipment/id/{id}")
	ResponseEntity<TripEquipment> findTripEquipmentById(@PathVariable Integer id, HttpServletResponse response) {
		TripEquipment tripequipment = teServ.findUserEquipmentById(id);
		if(tripequipment != null) {
			return new ResponseEntity<TripEquipment>(tripequipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "No Trip Equipment found with that id");
		}
	}

	@PostMapping("tripequipment/trip/{tripId}")
	ResponseEntity<TripEquipment> createTripEquipment(@RequestBody TripEquipment tripequipment,
						@PathVariable Integer tripId, 
						Principal principal) {
		TripEquipment newTripEquipment = teServ.createTripEquipment(tripequipment, principal.getName(), tripId);
		if(newTripEquipment != null) {
			return new ResponseEntity<TripEquipment>(newTripEquipment, HttpStatus.OK);
		}
		else {
			throw new ResourceNotUpdatedException(tripId, "Trip Equipment could not be created under that trip Id");
		}
	}
	
	@PutMapping("tripequipment/{id}")
	ResponseEntity<TripEquipment> updateTripEquipment(@PathVariable Integer id,
							Principal principal, 
							@RequestBody TripEquipment tripequipment) {
		TripEquipment teExists = teServ.findUserEquipmentById(id);
		if(teExists != null) {
			TripEquipment editTripEquipment = teServ.updateTripEquipment(id, tripequipment, principal.getName());
			if(editTripEquipment != null) {
				return new ResponseEntity<TripEquipment> (editTripEquipment, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "TripEquipment could not be updated");
			}			
		}
		else {
			throw new ResourceNotFoundException(id, "TripEquipment does not exist in db");
		}
		
	}
	
	@DeleteMapping("tripequipment/{id}")
	public ResponseEntity<Object> deleteTripEquipment(@PathVariable Integer id, Principal principal) {
		boolean deleted = false;
		deleted = teServ.deleteTripEquipment(id, principal.getName());
		if(deleted == true) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		throw new ResourceNotDeletedException(id, "TripEquipment could not be deleted");
	}


}
