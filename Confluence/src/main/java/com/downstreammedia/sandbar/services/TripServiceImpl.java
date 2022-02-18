package com.downstreammedia.sandbar.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements TripService and defines business logic
 * for manipulating trip entity
 * 
 * @author Diego Hoyos
 */
@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	TripRepository tripRepo;
	
	@Autowired
	UserRepository userRepo;
	
	/**
	 * Method returns all trips or null
	 * 
	 * @return - a list of all users
	 */
	@Override
	public List<Trip> findAllTrips() {
		return tripRepo.findAll();
	}
	
	/**
	 * Method returns trip with specific id value
	 * 
	 * @param id - trip to be found
	 * @return - a trip object
	 */
	@Override
	public Trip findTripById(int id) {
		Optional<Trip> trip = tripRepo.findById(id);
		if(trip.isPresent()) {
			return trip.get();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method returns trip with specific creator id value
	 * 
	 * @param id - user who created trip
	 * @return - a trip object
	 */
	public List<Trip> findTripByCreatorId(int id) {
		List<Trip> trip = tripRepo.findByCreator_Id(id);
		if(trip != null) {
			return trip;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method returns list of trips user is member of
	 * 
	 * @param id - user who is member
	 * @return - a list of trips or null
	 */
	public List<Trip> findTripByMemberId(int id) {
		List<Trip> trip = tripRepo.findByMembersId(id);
		if(trip != null) {
			return trip;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method edits existing Trip 
	 * 
	 * @param id - Trip to be updated
	 * @param trip - edited Trip object
	 * @param username - user performing the edit
	 * @return - a Trip object or null
	 */
	@Override
	public Trip updateTrip(int id, Trip trip, String username) {
		Optional<Trip> oldTrip = tripRepo.findById(id);
		User creator = userRepo.findByUsername(username);
		Trip managedTrip = null;
		if (oldTrip.isPresent()) {
			managedTrip = oldTrip.get();
			managedTrip.setId(id);
			managedTrip.setName(trip.getName());
			managedTrip.setDescription(trip.getDescription());
			managedTrip.setDateStart(trip.getDateStart());
			managedTrip.setDateEnd(trip.getDateEnd());
			if (creator != null) {
				return tripRepo.saveAndFlush(managedTrip);
			}			
		}
		return null;
	}
	
	/**
	 * Method adds a member to a trip
	 * 
	 * @param tripId - Trip that user is being added to
	 * @param userId - User to be added
	 * @param username - user performing the edit
	 * @return - a Trip object or null
	 */
	@Override
	public Trip addTripMember(int tripId, int userId, String username) {
		Optional<Trip> oldTrip = tripRepo.findById(tripId);
		Optional<User> oldUser = userRepo.findById(userId);
		User user = null;
		Set<User> members = null;
		Trip managedTrip = null;
		if (oldTrip.isPresent() && oldUser.isPresent()) {
			user = oldUser.get();
			managedTrip = oldTrip.get();
			members = managedTrip.getMembers();
			members.add(user);
			managedTrip.setMembers(members);
			return tripRepo.saveAndFlush(managedTrip);				
		}
		return null;
	}
	
	
	/**
	 * Method removes a member from trip 
	 * 
	 * @param tripId - Trip member is being removed from
	 * @param userId - User getting deleted
	 * @param username - user performing the edit
	 * @return - a user object or null
	 */
	@Override
	public Trip updateTripMember(int tripId, int userId, String username) {
		Optional<Trip> oldTrip = tripRepo.findById(tripId);
		Optional<User> oldUser = userRepo.findById(userId);
		User user = null;
		Set<User> members = null;
		Trip managedTrip = null;
		if (oldTrip.isPresent() && oldUser.isPresent()) {
			user = oldUser.get();
			managedTrip = oldTrip.get();
			members = managedTrip.getMembers();
			members.remove(user);
			managedTrip.setMembers(members);

			return tripRepo.saveAndFlush(managedTrip);
						
		}
		return null;
	}

	/**
	 * Method creates a new Trip 
	 * 
	 * @param trip - Trip object to be created
	 * @param username - user creating the trip
	 * @return - created Trip object or null
	 */
	@Override
	public Trip createTrip(Trip trip, String username) {
		Trip newTrip = null;
		User creator = userRepo.findByUsername(username);
		if (creator != null) {
			trip.setDateEnd(LocalDateTime.now());
			trip.setDateStart(LocalDateTime.now());
			trip.setCreator(creator);
			newTrip = tripRepo.saveAndFlush(trip);
		}
		return newTrip;			
	}

	/**
	 * Method deletes Trip with specific id value
	 * 
	 * @param id - Trip to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteTrip(int id, String username) {
		boolean answer = false;
		Optional<Trip> trip = tripRepo.findById(id);
		if (trip.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			tripRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}
}
