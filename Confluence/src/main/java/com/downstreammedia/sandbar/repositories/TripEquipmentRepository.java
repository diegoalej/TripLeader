package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.TripEquipment;

public interface TripEquipmentRepository extends JpaRepository <TripEquipment, Integer>{
	
	List<TripEquipment> findByTrip_Id(int id);

}
