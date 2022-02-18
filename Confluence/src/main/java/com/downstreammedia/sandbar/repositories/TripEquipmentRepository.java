package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.TripEquipment;

/**
 * Interface is JPA repository for TripEquipment class
 * 
 * @author Diego Hoyos
 */
public interface TripEquipmentRepository extends JpaRepository <TripEquipment, Integer>{
	
	List<TripEquipment> findByTrip_Id(int id);

}
