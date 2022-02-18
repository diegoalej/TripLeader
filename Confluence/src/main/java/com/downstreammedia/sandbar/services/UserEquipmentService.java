package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.UserEquipment;

/**
 * Interface defines methods to manipulate UserEquipment entity
 * 
 * @author Diego Hoyos
 */
public interface UserEquipmentService {
	
	List<UserEquipment> findAllUserEquipment();

	UserEquipment findUserEquipmentById(int id);
	
	List<UserEquipment> findUserEquipmentByUserId(int id);

	List<UserEquipment> findUserEquipmentByTripId(int id);

	UserEquipment createUserEquipment(UserEquipment equipment, String username, int id);

	UserEquipment updateUserEquipment(int id, UserEquipment equipment, String username);

	boolean deleteUserEquipment(int id, String username);

}
