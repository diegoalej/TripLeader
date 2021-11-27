package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.MealDay;

public interface MealDayRepository extends JpaRepository<MealDay, Integer>{
	
	List<MealDay> findByTrip_Id(int id);

}
