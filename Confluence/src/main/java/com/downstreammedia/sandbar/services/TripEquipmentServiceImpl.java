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

@Service
public class TripEquipmentServiceImpl implements TripEquipmentService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	TripEquipmentRepository teRepo;
	
	@Autowired
	TripRepository tripRepo;

	@Override
	public List<TripEquipment> findAllTripEquipment() {
		return teRepo.findAll();
	}

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
