package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;

public interface TripService {
	

	List<Trip> findAllTrips();

	Trip findTripById(int id);
	
	List<Trip> findTripByCreatorId(int id);
	
	List<Trip> findTripByMemberId(int id);
	
	Trip addTripMember(int id, User user, String username);
	
	Trip updateTripMember(int tripId, int userId, String username);

	Trip createTrip(Trip trip, String username);

	Trip updateTrip(int id, Trip trip, String username);

	boolean deleteTrip(int id, String username);

}
