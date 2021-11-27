package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Food;

public interface FoodService {
	
	List<Food> findAllFood();

	Food findFoodById(int id);
	
	Food findFoodByName(String name);

	Food createFood(Food food, String username, int mealId);

	Food updateFood(int id, Food food, String username);

	boolean deleteFood(int id, String username);

}
