package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.TripEquipment;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.TripEquipmentRepository;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements TripEquipmentService and defines
 * business logic for manipulating TripEquipment entity
 * 
 * @author Diego Hoyos
 */
@Service
public class TripEquipmentServiceImpl implements TripEquipmentService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	TripEquipmentRepository teRepo;
	
	@Autowired
	TripRepository tripRepo;
	
	/**
	 * Method returns all TripEquipment or null
	 * 
	 * @return - a list of all TripEquipment
	 */
	@Override
	public List<TripEquipment> findAllTripEquipment() {
		return teRepo.findAll();
	}
	
	/**
	 * Method returns TripEquipment with specific id value
	 * 
	 * @param id - TripEquipment to be found
	 * @return - a TripEquipment object
	 */
	@Override
	public TripEquipment findUserEquipmentById(int id) {
		Optional<TripEquipment> equipment = teRepo.findById(id);
		if(equipment.isPresent()) {
			return equipment.get();
		}
		else {			
			return null;
		}
	}
	
	/**
	 * Method returns TripEquipment with specific tripId value
	 * 
	 * @param id - Trip id 
	 * @return - a TripEquipment object
	 */
	@Override
	public List<TripEquipment> findTripEquipmentByTripId(int id) {
		List<TripEquipment> equipment = teRepo.findByTrip_Id(id);
		if(equipment != null) {
				return equipment;
			}
			else {			
				return null;
			}
	}
	
	/**
	 * Method creates a new TripEquipment  
	 * 
	 * @param equipment - user object to be created
	 * @param username - user object to be created
	 * @param id - user object to be created
	 * @return - created TripEquipment object or null
	 */
	@Override
	public TripEquipment createTripEquipment(TripEquipment equipment, String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Trip> trip = tripRepo.findById(id);
		if (user != null && trip.isPresent()) {
			equipment.setTrip(trip.get());
			TripEquipment newEquipment= teRepo.saveAndFlush(equipment);
			if(newEquipment != null) {
				return newEquipment;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method edits existing TripEquipment 
	 * 
	 * @param id - TripEquipment to be updated
	 * @param equipment - edited TripEquipment object
	 * @param username - user performing the edit
	 * @return - a TripEquipment object or null
	 */
	@Override
	public TripEquipment updateTripEquipment(int id, TripEquipment equipment, String username) {
		Optional<TripEquipment> oldEquipment = teRepo.findById(id);
		TripEquipment managedEquipment = null;
		User user = userRepo.findByUsername(username);
		if (oldEquipment.isPresent()) {
			managedEquipment = oldEquipment.get();
			managedEquipment.setAmount(equipment.getAmount());
			managedEquipment.setActive(equipment.isActive());
			if (user != null 
					//Allowing only creator or admin to edit
					//&& user.getId() == oldExpense.get().getCreator().getId()
					) {
				return teRepo.saveAndFlush(managedEquipment);
			}
		}
		return managedEquipment;
	}
	
	/**
	 * Method deletes TripEquipment with specific id value
	 * 
	 * @param id - TripEquipment to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteTripEquipment(int id, String username) {
		boolean answer = false;
		Optional<TripEquipment> equipment = teRepo.findById(id);
		if(equipment.isPresent() && 
				userRepo.findByUsername(username).getRole().equals("admin")) {
			teRepo.deleteById(id);
			answer = true;
		}
		return answer;
	}
	
	
}
