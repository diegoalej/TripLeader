package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Location;
import com.downstreammedia.sandbar.repositories.LocationRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements LocationService and defines business logic
 * for manipulating Location entity
 * 
 * @author Diego Hoyos
 */
@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;

	@Autowired
	UserRepository userRepo;
	
	/**
	 * Method returns all Location or null
	 * 
	 * @return - a list of all Locations
	 */
	@Override
	public List<Location> findAllLocations() {
		return locationRepo.findAll();
	}
	
	/**
	 * Method returns Location with specific id value
	 * 
	 * @param id - Location to be found
	 * @return - a Location object or null
	 */
	@Override
	public Location findLocationById(int id) {
		Optional<Location> location = locationRepo.findById(id);
		if(location.isPresent()) {
			return location.get();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method returns Location with specific name
	 * 
	 * @param name - string of location name to be found
	 * @return - a Location object or null
	 */
	@Override
	public List <Location> findLocationByName(String name) {
		List <Location> location = locationRepo.findByName(name);
		if(location.size() > 0) {
			return location;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method creates a new Location 
	 * 
	 * @param location - Location object to be created
	 * @return - created Location object or null
	 */
	@Override
	public Location createLocation(Location location, String username) {
		Location newLocation = null;
		if (userRepo.findByUsername(username) != null) {
			newLocation = locationRepo.saveAndFlush(location);
		}
		return newLocation;			
	}
	
	/**
	 * Method edits existing Location 
	 * 
	 * @param id - Location to be updated
	 * @param location - edited Location object
	 * @param username - user performing the edit
	 * @return - a Location object or null
	 */
	@Override
	public Location updateLocation(int id, Location location, String username) {
		Optional<Location> oldLocation = locationRepo.findById(id);
		Location managedLocation = null;
		if (oldLocation.isPresent()) {
			managedLocation = oldLocation.get();
			managedLocation.setId(id);
			managedLocation.setName(location.getName());
			managedLocation.setDescription(location.getDescription());
			managedLocation.setAddress(location.getAddress());
			managedLocation.setZip(location.getZip());
			managedLocation.setCity(location.getCity());
			managedLocation.setLatitude(location.getLatitude());
			managedLocation.setLongitude(location.getLongitude());
			if (userRepo.findByUsername(username) != null) {
				return locationRepo.saveAndFlush(managedLocation);
			}			
		}
		return null;
	}
	
	/**
	 * Method deletes Location with specific id value
	 * 
	 * @param id - Location to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteLocation(int id, String username) {
		boolean answer = false;
		Optional<Location> location = locationRepo.findById(id);
		if (location.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			locationRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}

}
