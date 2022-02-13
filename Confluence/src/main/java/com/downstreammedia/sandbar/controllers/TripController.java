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

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.TripService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })//Angular local port 
public class TripController {
	
	@Autowired
	private TripService tripServ;
	
	
	@GetMapping("trips")
	private ResponseEntity<List<Trip>> getAllTrips(){
		List<Trip> trips = tripServ.findAllTrips();
		if (trips.size() > 0) {
			return new ResponseEntity<List<Trip>>(trips, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No trips found");
		}
	}
	
	
	@GetMapping("trips/id/{id}")
	public ResponseEntity<Trip> getTripWithId(@PathVariable Integer id){
		Trip trip = tripServ.findTripById(id);
		if (trip != null) {
			return new ResponseEntity<Trip>(trip, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Trip does not exist in database");
		}
	}

	
	
	@PostMapping("trips")
	public ResponseEntity<Trip> createNewTrip (@RequestBody Trip trip, 
											   Principal principal){
		Trip newTrip = tripServ.createTrip(trip, principal.getName());
		if (newTrip != null) {
			return new ResponseEntity<Trip>(trip, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotUpdatedException(0, "Trip could not be created");
		}
	}
	
	
	@PutMapping("trips/{id}")
	public ResponseEntity<Trip> updateExistingTrip(
			@RequestBody Trip trip, 
			@PathVariable int id, 
			Principal principal
			){
		Trip tripExists = tripServ.findTripById(id);
		if(tripExists != null) {
			Trip newTrip = tripServ.updateTrip(id, tripExists, principal.getName() );
			if (newTrip != null) {
				return new ResponseEntity<Trip>(newTrip, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "User could not be updated");
			}
		} 
		else {
			throw new ResourceNotFoundException(id, "User does not exist in database");
		}
		
	}

	@PutMapping("trips/member/{tripId}/{userId}")
	public ResponseEntity<Trip> addTripMember(
			@PathVariable int userId, 
			@PathVariable int tripId, 
			Principal principal
			){
		Trip tripExists = tripServ.findTripById(tripId);
		if(tripExists != null) {
			Trip newTrip = tripServ.addTripMember(tripId, userId, principal.getName() );
			if (newTrip != null) {
				return new ResponseEntity<Trip>(newTrip, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(userId, "User could not be added to Trip "+tripId);
			}
		} 
		else {
			throw new ResourceNotFoundException(tripId, "Trip does not exist in database");
		}
		
	}

	@PutMapping("trips/member/update/{tripId}/{userId}")
	public ResponseEntity<Trip> updatedTripMember(
			@PathVariable int userId, 
			@PathVariable int tripId, 
			Principal principal
			){
		Trip tripExists = tripServ.findTripById(tripId);
		if(tripExists != null) {
			Trip newTrip = tripServ.updateTripMember(tripId, userId, principal.getName() );
			if (newTrip != null) {
				return new ResponseEntity<Trip>(newTrip, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(userId, "User could not be updated in Trip "+tripId);
			}
		} 
		else {
			throw new ResourceNotFoundException(tripId, "Trip does not exist in database");
		}
		
	}
	
	@DeleteMapping("trips/{id}")
	public ResponseEntity<Object> deleteTrip(	@PathVariable int id, 
												HttpServletResponse response,
												Principal principal){
			boolean deleted = false;
			deleted = tripServ.deleteTrip(id, principal.getName());
			if (deleted == true) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}				
			throw new ResourceNotDeletedException(id, "User could not be deleted");
	}
	
	@GetMapping("trips/creator/{id}")
	public ResponseEntity<List<Trip>> getTripsCreatedWithCreatorId(@PathVariable int id, HttpServletResponse response){
		List<Trip> trip = tripServ.findTripByCreatorId(id);
		if (trip != null && trip.size() > 0) {
			return new ResponseEntity<List<Trip>>(trip, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "User does not have any created trips");
		}
	}

	@GetMapping("trips/member/{id}")
	public ResponseEntity<List<Trip>> getTripsMemberOfByCreatorId(@PathVariable int id, HttpServletResponse response){
		List<Trip> trip = tripServ.findTripByMemberId(id);
		if (trip != null && trip.size() > 0) {
			return new ResponseEntity<List<Trip>>(trip, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "User is not a member of any trips");
		}
	}

}
