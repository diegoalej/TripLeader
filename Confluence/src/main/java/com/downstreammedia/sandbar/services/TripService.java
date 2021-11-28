package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Trip;

public interface TripService {
	

	List<Trip> findAllTrips();

	Trip findTripById(int id);
	
	List<Trip> findTripByCreatorId(int id);

	Trip createTrip(Trip trip, String username);

	Trip updateTrip(int id, Trip trip, String username);

	boolean deleteTrip(int id, String username);

}
