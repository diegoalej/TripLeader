package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{
	
	Food findByName(String name); 

}
