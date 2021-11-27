package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Meal;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.MealRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	MealRepository mealRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	@Override
	public List<Meal> findAllMeals() {
		return mealRepo.findAll();
	}
	
	@Override
	public Meal findMealById(int id) {
		Optional<Meal> meal = mealRepo.findById(id);
		if(meal.isPresent()) {
			return meal.get();
		}
		else {
			return null;
		}
	}
	
	public Meal findMealByName(String name) {
		Meal meal = mealRepo.findByName(name);
		if(meal != null) {
			return meal;
		}
		else {
			return null;
		}
	}
	
	@Override
	public Meal updateMeal(int id, Meal meal, String username) {
		Optional<Meal> oldMeal = mealRepo.findById(id);
		Meal managedMeal = null;
		if (oldMeal.isPresent()) {
			managedMeal = oldMeal.get();
			managedMeal.setId(id);
			managedMeal.setName(meal.getName());
			managedMeal.setDescription(meal.getDescription());
			managedMeal.setMealtype(meal.getMealtype());
			managedMeal.setDayserved(meal.getDayserved());
			managedMeal.setLocation(meal.getLocation());
			if (userRepo.findByUsername(username) != null) {
				return mealRepo.saveAndFlush(managedMeal);
			}			
		}
		return null;
	}
	
	@Override
	public Meal createMeal(Meal meal, String username) {
		User user = userRepo.findByUsername(username);
		Meal newMeal = null;
		if (userRepo.findByUsername(username) != null) {
			meal.setCreator(user);
			meal.setDayserved(meal.getDayserved());
			meal.setLocation(meal.getLocation());
			meal.setMealtype(meal.getMealtype());
			newMeal = mealRepo.saveAndFlush(meal);
		}
		return newMeal;			
	}
	
	@Override
	public boolean deleteMeal(int id, String username) {
		boolean answer = false;
		Optional<Meal> meal = mealRepo.findById(id);
		if (meal.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			mealRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}


}
