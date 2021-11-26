package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Equipment;

public interface EquipmentService {
	
	List<Equipment> findAllEquipment();

	Equipment findEquipmentById(int id);
	
	Equipment findEquipmentByName(String name);

	Equipment createEquipment(Equipment equipment, String username);

	Equipment updateEquipment(int id, Equipment equipment, String username);

	boolean deleteEquipment(int id, String username);

}
