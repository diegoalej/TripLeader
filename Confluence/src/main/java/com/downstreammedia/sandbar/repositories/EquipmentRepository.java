package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Equipment;

/**
 * Interface is JPA repository for Equipment class
 * 
 * @author Diego Hoyos
 */
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
	
	Equipment findByName(String name); 

}
