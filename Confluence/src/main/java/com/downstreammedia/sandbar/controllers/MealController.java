package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.Meal;
import com.downstreammedia.sandbar.services.MealService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class MealController {

	@Autowired
	private MealService mealServ;
	
	@GetMapping("meals")
	private List<Meal> getAllMeals(HttpServletResponse response){
		List<Meal> meal = mealServ.findAllMeals();
		if (meal.size() > 0) {
			return meal;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("meals/id/{id}")
	public Meal getMealWithId(@PathVariable Integer id, HttpServletResponse response){
		Meal meal = mealServ.findMealById(id);
		if (meal != null) {
			return meal;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("meals/{name}")
	public Meal getMealWithName(@PathVariable String name, HttpServletResponse response){
		Meal meal = mealServ.findMealByName(name);
		if (meal != null) {
			return meal;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("meals")
	public Meal createNewMeal(
			@RequestBody Meal meal, 
			HttpServletResponse response,
			Principal principal
			){
		Meal newMeal = mealServ.createMeal(meal, principal.getName());
		if (newMeal != null) {
			return newMeal;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("meals/{id}")
	public Meal updateExistingMeal(
			@RequestBody Meal meal, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Meal editMeal = mealServ.updateMeal(id, meal, principal.getName());
		if (editMeal != null) {
			return editMeal;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("meals/{id}")
	public void deleteMeal(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
//		Location loc = locationServ.findLocationById(id);
//		if(loc.getReviews().size() > 0) {
//			for (Review review : loc.getReviews()) {
//				revSvc.deleteReview(review.getId(), principal.getName());
//			}
//		}
		try {
			deleted = mealServ.deleteMeal(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}
}
