package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	TripRepository tripRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Trip> findAllTrips() {
		return tripRepo.findAll();
	}
	
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
	
	public List<Trip> findTripByCreatorId(int id) {
		List<Trip> trip = tripRepo.findByCreator_Id(id);
		if(trip != null) {
			return trip;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Trip updateTrip(int id, Trip trip, String username) {
		Optional<Trip> oldTrip = tripRepo.findById(id);
		Trip managedTrip = null;
		if (oldTrip.isPresent()) {
			managedTrip = oldTrip.get();
			managedTrip.setId(id);
			managedTrip.setName(trip.getName());
			managedTrip.setDescription(trip.getDescription());
			managedTrip.setDateStart(trip.getDateStart());
			managedTrip.setDateEnd(trip.getDateEnd());
			if (userRepo.findByUsername(username) != null) {
				return tripRepo.saveAndFlush(managedTrip);
			}			
		}
		return null;
	}
	
	@Override
	public Trip createTrip(Trip trip, String username) {
		Trip newTrip = null;
		User creator = userRepo.findByUsername(username);
		if (creator != null) {
			trip.setCreator(creator);
			newTrip = tripRepo.saveAndFlush(trip);
		}
		return newTrip;			
	}
	
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
