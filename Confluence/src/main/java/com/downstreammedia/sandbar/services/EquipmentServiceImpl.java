package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Equipment;
import com.downstreammedia.sandbar.repositories.EquipmentRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	EquipmentRepository equipmentRepo;

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Equipment> findAllEquipment() {
		return equipmentRepo.findAll();
	}
	
	@Override
	public Equipment findEquipmentById(int id) {
		Optional<Equipment> equipment = equipmentRepo.findById(id);
		if(equipment.isPresent()) {
			return equipment.get();
		}
		else {
			return null;
		}
	}
	
	public Equipment findEquipmentByName(String name) {
		Equipment equipment = equipmentRepo.findByName(name);
		if(equipment != null) {
			return equipment;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Equipment updateEquipment(int id, Equipment equipment, String username) {
		Optional<Equipment> oldLocation = equipmentRepo.findById(id);
		Equipment managedEquipment = null;
		if (oldLocation.isPresent()) {
			managedEquipment = oldLocation.get();
			managedEquipment.setId(id);
			managedEquipment.setName(equipment.getName());
			managedEquipment.setDescription(equipment.getDescription());
			if (userRepo.findByUsername(username) != null) {
				return equipmentRepo.saveAndFlush(managedEquipment);
			}			
		}
		return null;
	}
	
	@Override
	public Equipment createEquipment(Equipment equipment, String username) {
		Equipment newEquipment = null;
		if (userRepo.findByUsername(username) != null) {
			newEquipment = equipmentRepo.saveAndFlush(equipment);
		}
		return newEquipment;			
	}
	
	@Override
	public boolean deleteEquipment(int id, String username) {
		boolean answer = false;
		Optional<Equipment> equipment = equipmentRepo.findById(id);
		if (equipment.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			equipmentRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}
}
