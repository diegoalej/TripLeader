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

import com.downstreammedia.sandbar.entities.Food;
import com.downstreammedia.sandbar.services.FoodService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class FoodController {
	
	@Autowired
	private FoodService foodServ;
	
	@GetMapping("food")
	private List<Food> getAllFood(HttpServletResponse response){
		List<Food> food = foodServ.findAllFood();
		if (food.size() > 0) {
			return food;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("food/id/{id}")
	public Food getFoodWithId(@PathVariable Integer id, HttpServletResponse response){
		Food food = foodServ.findFoodById(id);
		if (food != null) {
			return food;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("food/{name}")
	public Food getFoodWithName(@PathVariable String name, HttpServletResponse response){
		Food food = foodServ.findFoodByName(name);
		if (food != null) {
			return food;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("food/{id}")
	public Food createNewFood(
			@PathVariable int id,
			@RequestBody Food food, 
			HttpServletResponse response,
			Principal principal
			){
		Food newFood = foodServ.createFood(food, principal.getName(), id);
		if (newFood != null) {
			return newFood;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("food/{id}")
	public Food updateExistingFood(
			@RequestBody Food food, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Food editFood = foodServ.updateFood(id, food, principal.getName());
		if (editFood != null) {
			return editFood;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("food/{id}")
	public void deleteFood(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
		try {
			deleted = foodServ.deleteFood(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
