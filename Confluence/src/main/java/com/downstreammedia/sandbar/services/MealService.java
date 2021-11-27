package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Meal;

public interface MealService {
	
	List<Meal> findAllMeals();

	Meal findMealById(int id);
	
	Meal findMealByName(String name);

	Meal createMeal(Meal meal, String username);

	Meal updateMeal(int id, Meal meal, String username);

	boolean deleteMeal(int id, String username);

}
