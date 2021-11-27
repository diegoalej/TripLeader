package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Food;
import com.downstreammedia.sandbar.entities.Meal;
import com.downstreammedia.sandbar.repositories.FoodRepository;
import com.downstreammedia.sandbar.repositories.MealRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	FoodRepository foodRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MealRepository mealRepo;
	
	@Override
	public List<Food> findAllFood() {
		return foodRepo.findAll();
	}
	
	@Override
	public Food findFoodById(int id) {
		Optional<Food> food = foodRepo.findById(id);
		if(food.isPresent()) {
			return food.get();
		}
		else {
			return null;
		}
	}
	
	public Food findFoodByName(String name) {
		Food food = foodRepo.findByName(name);
		if(food != null) {
			return food;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Food updateFood(int id, Food food, String username) {
		Optional<Food> oldFood = foodRepo.findById(id);
		Food managedFood = null;
		if (oldFood.isPresent()) {
			managedFood = oldFood.get();
			managedFood.setId(id);
			managedFood.setName(food.getName());
			managedFood.setDescription(food.getDescription());
			managedFood.setQuantity(food.getQuantity());
			if (userRepo.findByUsername(username) != null) {
				return foodRepo.saveAndFlush(managedFood);
			}			
		}
		return null;
	}
	
	@Override
	public Food createFood(Food food, String username, int mealId) {
		Optional<Meal> meal = mealRepo.findById(mealId);
		Food newFood = null;
		if (userRepo.findByUsername(username) != null) {
			food.setMeal(meal.get());
			newFood = foodRepo.saveAndFlush(food);
		}
		return newFood;			
	}
	
	@Override
	public boolean deleteFood(int id, String username) {
		boolean answer = false;
		Optional<Food> food = foodRepo.findById(id);
		if (food.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			foodRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}

}
