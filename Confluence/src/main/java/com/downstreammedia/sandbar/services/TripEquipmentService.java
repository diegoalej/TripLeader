package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.TripEquipment;

/**
 * Interface defines methods to manipulate TripEquipment entity
 * 
 * @author Diego Hoyos
 */
public interface TripEquipmentService {
	
	List<TripEquipment> findAllTripEquipment();

	TripEquipment findUserEquipmentById(int id);
	
	List<TripEquipment> findTripEquipmentByTripId(int id);

	TripEquipment createTripEquipment(TripEquipment equipment, String username, int id);

	TripEquipment updateTripEquipment(int id, TripEquipment equipment, String username);

	boolean deleteTripEquipment(int id, String username);

}
