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

import com.downstreammedia.sandbar.entities.TripEquipment;
import com.downstreammedia.sandbar.services.TripEquipmentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 4220
public class TripEquipmentController {
	
	@Autowired
	TripEquipmentService teServ;
	
	@GetMapping("tripequipment")
	List<TripEquipment> getAllTripEquipment(HttpServletResponse response){
		List<TripEquipment> tripequipment = teServ.findAllTripEquipment();
		if(tripequipment.size() > 0) {
			return tripequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	

	@GetMapping("tripequipment/trip/{id}")
	List<TripEquipment> findTripEquipmentByTripId(@PathVariable Integer id, HttpServletResponse response) {
		List<TripEquipment> tripequipment = teServ.findTripEquipmentByTripId(id);
		if(tripequipment.size() > 0) {
			return tripequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("tripequipment/id/{id}")
	TripEquipment findTripEquipmentById(@PathVariable Integer id, HttpServletResponse response) {
		TripEquipment tripequipment = teServ.findUserEquipmentById(id);
		if(tripequipment != null) {
			return tripequipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@PostMapping("tripequipment/trip/{tripId}")
	TripEquipment createTripEquipment(@RequestBody TripEquipment tripequipment,
						@PathVariable Integer tripId, 
						HttpServletResponse response,
						Principal principal
						) {
		TripEquipment newTripEquipment = teServ.createTripEquipment(tripequipment, principal.getName(), tripId);
		if(newTripEquipment != null) {
			return newTripEquipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("tripequipment/{id}")
	TripEquipment updateTripEquipment(@PathVariable Integer id,
							Principal principal, 
							HttpServletResponse response,
							@RequestBody TripEquipment tripequipment) {
		TripEquipment editTripEquipment = teServ.updateTripEquipment(id, tripequipment, principal.getName());
		if(editTripEquipment != null) {
			return editTripEquipment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("tripequipment/{id}")
	public void deleteTripEquipment(@PathVariable Integer id,
							Principal principal,
							HttpServletResponse response) {
		boolean deleted = false;
		try {
			deleted = teServ.deleteTripEquipment(id, principal.getName());
			if(deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {
			response.setStatus(404);
		}
	}


}
