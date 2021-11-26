package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.MealType;

public interface MealTypeService {
	
	List<MealType> findAllMealTypes();
	
	MealType findMealTypeById(int id);
		
	MealType createMealType(MealType mealtype);
	
	MealType updateMealType(int id, MealType mealtype, String username);
	
	boolean deleteMealType(int id, String username);

}
