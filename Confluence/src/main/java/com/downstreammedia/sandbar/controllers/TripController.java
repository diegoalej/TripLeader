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
import com.downstreammedia.sandbar.services.TripService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })//Angular local port 4220
public class TripController {
	
	@Autowired
	private TripService tripServ;
	
	@GetMapping("trips")
	private List<Trip> getAllTrips(HttpServletResponse response){
		List<Trip> trip = tripServ.findAllTrips();
		if (trip.size() > 0) {
			return trip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("trips/id/{id}")
	public Trip getTripWithId(@PathVariable Integer id, HttpServletResponse response){
		Trip trip = tripServ.findTripById(id);
		if (trip != null) {
			return trip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	
	@PostMapping("trips")
	public Trip createNewTrip (
			@RequestBody Trip trip, 
			HttpServletResponse response,
			Principal principal
			){
		Trip newTrip = tripServ.createTrip(trip, principal.getName());
		if (newTrip != null) {
			return newTrip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("trips/{id}")
	public Trip updateExistingTrip(
			@RequestBody Trip trip, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Trip editTrip = tripServ.updateTrip(id, trip, principal.getName());
		if (editTrip != null) {
			return editTrip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@PutMapping("trips/member/{tripId}/{userId}")
	public Trip addTripMember(
			@PathVariable int userId, 
			@PathVariable int tripId, 
			HttpServletResponse response,
			Principal principal
			){
		Trip editTrip = tripServ.addTripMember(tripId, userId, principal.getName());
		if (editTrip != null) {
			return editTrip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@PutMapping("trips/member/update/{tripId}/{userId}")
	public Trip updatedTripMember(
			@PathVariable int userId, 
			@PathVariable int tripId, 
			HttpServletResponse response,
			Principal principal
			){
		Trip editTrip = tripServ.updateTripMember(tripId, userId, principal.getName());
		if (editTrip != null) {
			return editTrip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("trips/{id}")
	public void deleteTrip(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
		try {
			deleted = tripServ.deleteTrip(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}
	
	@GetMapping("trips/creator/{id}")
	public List<Trip> getTripsCreatedWithCreatorId(@PathVariable int id, HttpServletResponse response){
		List<Trip> trip = tripServ.findTripByCreatorId(id);
		if (trip != null) {
			return trip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("trips/member/{id}")
	public List<Trip> getTripsMemberOfByCreatorId(@PathVariable int id, HttpServletResponse response){
		List<Trip> trip = tripServ.findTripByMemberId(id);
		if (trip != null) {
			return trip;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

}
