package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.UserEquipment;

/**
 * Interface is JPA repository for UserEquipment class
 * 
 * @author Diego Hoyos
 */
public interface UserEquipmentRepository extends JpaRepository <UserEquipment, Integer>{

	List<UserEquipment> findByCreator_Id(int id);
	
	List<UserEquipment> findByTrip_Id(int id);
}
