package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Location;

/**
 * Interface defines methods to manipulate Location entity
 * 
 * @author Diego Hoyos
 */
public interface LocationService {
	
	List<Location> findAllLocations();
	
	Location findLocationById(int id);

	List <Location> findLocationByName(String name);

	Location createLocation(Location location, String username);

	Location updateLocation(int id, Location location, String username);

	boolean deleteLocation(int id, String username);

}
