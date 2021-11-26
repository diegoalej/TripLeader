package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Location;
import com.downstreammedia.sandbar.repositories.LocationRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	LocationRepository locationRepo;

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Location> findAllLocations() {
		return locationRepo.findAll();
	}
	
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
	
	@Override
	public Location createLocation(Location location, String username) {
		//User creator = userRepo.findByUsername(username);
		//location.setCreator(creator);
		//location.setDateCreated(LocalDateTime.now());
		Location newLocation = null;
		if (userRepo.findByUsername(username) != null) {
			newLocation = locationRepo.saveAndFlush(location);
		}
		return newLocation;			
	}
	
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
