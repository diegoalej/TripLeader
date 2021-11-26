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

import com.downstreammedia.sandbar.entities.Location;
import com.downstreammedia.sandbar.services.LocationService;

@RestController
@RequestMapping ("api")
@CrossOrigin({"*", "http://localhost:4220"}) // Angular local port
public class LocationController {
	
	@Autowired
	private LocationService locationServ;
	
	@GetMapping("locations")
	private List<Location> getAllLocations(HttpServletResponse response){
		List<Location> locations = locationServ.findAllLocations();
		if (locations.size() > 0) {
			return locations;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("locations/{id}")
	public Location getLocationWithId(@PathVariable Integer id, HttpServletResponse response){
		Location location = locationServ.findLocationById(id);
		if (location != null) {
			return location;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("locations")
	public Location createNewLocation(
			@RequestBody Location location, 
			HttpServletResponse response,
			Principal principal
			){
		Location newLocation = locationServ.createLocation(location, principal.getName());
		if (newLocation != null) {
			return newLocation;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("locations/{id}")
	public Location updateExistingLocation(
			@RequestBody Location location, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Location editLocation = locationServ.updateLocation(id, location, principal.getName());
		if (editLocation != null) {
			return editLocation;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("locations/{id}")
	public void deleteLocation(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
//		Location loc = locationServ.findLocationById(id);
//		if(loc.getReviews().size() > 0) {
//			for (Review review : loc.getReviews()) {
//				revSvc.deleteReview(review.getId(), principal.getName());
//			}
//		}
		try {
			deleted = locationServ.deleteLocation(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
