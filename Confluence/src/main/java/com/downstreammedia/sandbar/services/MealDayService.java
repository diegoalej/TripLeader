package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.MealDay;

public interface MealDayService {
	
	List<MealDay> findAllMealDays();

	MealDay findMealDayById(int id);
	
	List<MealDay> findMealDayByTripId(int id);

	MealDay createMealDay(MealDay mealDay, String username, int id);

	MealDay updateMealDay(int id, MealDay mealDay, String username);

	boolean deleteMealDay(int id, String username);

}
