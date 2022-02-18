package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.entities.UserEquipment;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserEquipmentRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements UserEquipmentService and defines 
 * business logic for manipulating UserEquipment entity
 * 
 * @author Diego Hoyos
 */
@Service
public class UserEquipmentServiceImpl implements UserEquipmentService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserEquipmentRepository ueRepo;
	
	@Autowired
	TripRepository tripRepo;
	
	/**
	 * Method returns all of a users equipment or null
	 * 
	 * @return - a list of all UserEquipment
	 */
	@Override
	public List<UserEquipment> findAllUserEquipment() {
		return ueRepo.findAll();
	}
	
	/**
	 * Method returns UserEquipment with specific id value
	 * 
	 * @param id - UserEquipment to be found
	 * @return - a UserEquipment object
	 */
	@Override
	public UserEquipment findUserEquipmentById(int id) {
		Optional<UserEquipment> equipment = ueRepo.findById(id);
		if(equipment.isPresent()) {
			return equipment.get();
		}
		else {			
			return null;
		}
	}
	
	/**
	 * Method returns UserEquipment with specific user id value
	 * 
	 * @param id - user id 
	 * @return - a list of equipment or null
	 */
	@Override
	public List<UserEquipment> findUserEquipmentByUserId(int id) {
		List<UserEquipment> equipment = ueRepo.findByCreator_Id(id);
		if(equipment != null) {
				return equipment;
			}
			else {			
				return null;
			}
	}
	
	/**
	 * Method returns UserEquipment with specific trip id value
	 * 
	 * @param id - trip id 
	 * @return - a list of equipment or null
	 */
	@Override
	public List<UserEquipment> findUserEquipmentByTripId(int id) {
		List<UserEquipment> equipment = ueRepo.findByTrip_Id(id);
		if(equipment != null) {
				return equipment;
			}
			else {			
				return null;
			}
	}
	
	/**
	 * Method creates a new UserEquipment 
	 * 
	 * @param equipment - UserEquipment object to be created
	 * @param username - creator of UserEquipment
	 * @param id - trip id to create equipment under
	 * @return - created UserEquipment object or null
	 */
	@Override
	public UserEquipment createUserEquipment(UserEquipment equipment, String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Trip> trip = tripRepo.findById(id);
		if (user != null && trip.isPresent()) {
			equipment.setCreator(user);
			equipment.setEquipment(equipment.getEquipment());
			equipment.setTrip(trip.get());
			UserEquipment newEquipment= ueRepo.saveAndFlush(equipment);
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
	 * Method edits existing UserEquipment 
	 * 
	 * @param id - UserEquipment to be updated
	 * @param equipment - edited UserEquipment object
	 * @param username - user performing the edit
	 * @return - a user object or null
	 */
	@Override
	public UserEquipment updateUserEquipment(int id, UserEquipment equipment, String username) {
		Optional<UserEquipment> oldEquipment = ueRepo.findById(id);
		UserEquipment managedEquipment = null;
		User user = userRepo.findByUsername(username);
		if (oldEquipment.isPresent()) {
			managedEquipment = oldEquipment.get();
			managedEquipment.setAmount(equipment.getAmount());
			managedEquipment.setActive(equipment.isActive());
			managedEquipment.setCreator(user);
			if (user != null 
					//Allowing only creator or admin to edit
					//&& user.getId() == oldExpense.get().getCreator().getId()
					) {
				return ueRepo.saveAndFlush(managedEquipment);
			}
		}
		return managedEquipment;
	}
	
	/**
	 * Method deletes UserEquipment with specific id value
	 * 
	 * @param id - UserEquipment to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteUserEquipment(int id, String username) {
		boolean answer = false;
		Optional<UserEquipment> equipment = ueRepo.findById(id);
		if(equipment.isPresent() && 
				userRepo.findByUsername(username).getRole().equals("admin")) {
			ueRepo.deleteById(id);
			answer = true;
		}
		return answer;
	}
}
