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

import com.downstreammedia.sandbar.entities.Location;
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.LocationService;

/**
 * Class is REST controller for Location 
 * 
 * @author Diego Hoyos
 */
@RestController
@RequestMapping ("api")
@CrossOrigin({"*", "http://localhost:4220"}) // Angular local port
public class LocationController {
	
	@Autowired
	private LocationService locationServ;
	
	/**
	 * Endpoint returns all Locations or null
	 * 
	 * @return - a list of all Locations
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("locations")
	private ResponseEntity<List<Location>> getAllLocations(){
		List<Location> locations = locationServ.findAllLocations();
		if (locations.size() > 0) {
			return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No locations found");
		}
	}
	
	/**
	 * Endpoint returns Location with specific trip id value
	 * 
	 * @param id - Location id to be found
	 * @return - a Location object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("locations/id/{id}")
	public ResponseEntity<Location> getLocationWithId(@PathVariable Integer id, HttpServletResponse response){ 
		Location location = locationServ.findLocationById(id);
		if (location != null) {
			return new ResponseEntity<Location>(location, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "User could not be found with that id");
		}
	}
	
	/**
	 * Endpoint returns Location with specific name value
	 * 
	 * @param name - Location name to be found
	 * @return - a Location object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("locations/{name}")
	public ResponseEntity<List<Location>> getLocationWithName(@PathVariable String name){
		List <Location> location = locationServ.findLocationByName(name);
		if (location != null) {
			return new ResponseEntity<List<Location>>(location, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "User could not be found with name " + name);
		}
	}
	
	/**
	 * Endpoint creates new Location
	 * 
	 * @param location - Location object to be created
	 * @param principal - user making the edit
	 * @return - a Location object
	 * @throws - ResourceNotFoundException
	 */
	@PostMapping("locations")
	public  ResponseEntity<Location> createNewLocation(@RequestBody Location location, Principal principal){
		Location newLocation = locationServ.createLocation(location, principal.getName());
		if (newLocation != null) {
			return  new ResponseEntity<Location>(location, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotFoundException(location.getId(), "location could not be found with that id");
		}
	}
	
	/**
	 * Endpoint edits existing Location 
	 * 
	 * @param id - Location to be updated
	 * @param location - edited Location object
	 * @param principal - user performing the edit
	 * @return - a Location object or null
	 * @throws - ResourceNotUpdatedException
	 * @throws - ResourceNotFoundException
	 */
	@PutMapping("locations/{id}")
	public ResponseEntity<Location> updateExistingLocation(
			@RequestBody Location location, 
			@PathVariable int id, 
			Principal principal
			){
		Location editLocation = locationServ.updateLocation(id, location, principal.getName());
		if (editLocation != null) {
			return new ResponseEntity<Location>(editLocation, HttpStatus.OK);
		}
		else {
			throw new ResourceNotUpdatedException(id, "Location could not be updated");
		}
	}
	
	/**
	 * Endpoint deletes Location with specific id value
	 * 
	 * @param id - Location to be deleted
	 * @param principal - user performing the delete
	 * @return - boolean with result
	 * @throws - ResourceNotDeletedException
	 */
	@DeleteMapping("locations/{id}")
	public ResponseEntity<Location> deleteLocation(@PathVariable int id, Principal principal){
		boolean deleted = false;
			deleted = locationServ.deleteLocation(id, principal.getName());
			if (deleted == true) {
				return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
			}
			else {				
				throw new ResourceNotDeletedException(id, "Location could not be deleted");
			}
	}	

}
