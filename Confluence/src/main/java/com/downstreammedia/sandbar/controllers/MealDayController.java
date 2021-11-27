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

import com.downstreammedia.sandbar.entities.MealDay;
import com.downstreammedia.sandbar.services.MealDayService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class MealDayController {
	
	@Autowired
	private MealDayService mdServ;
	
	@GetMapping("mealday")
	private List<MealDay> getAllMealDays(HttpServletResponse response){
		List<MealDay> mealDay = mdServ.findAllMealDays();
		if (mealDay.size() > 0) {
			return mealDay;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("mealday/id/{id}")
	public MealDay getMealDayWithId(@PathVariable Integer id, HttpServletResponse response){
		MealDay mealDay = mdServ.findMealDayById(id);
		if (mealDay != null) {
			return mealDay;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("mealday/trip/{id}")
	public List<MealDay> getMealDayByTripId(@PathVariable int id, HttpServletResponse response){
		List<MealDay> mealDay = mdServ.findMealDayByTripId(id);
		if (mealDay != null) {
			return mealDay;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("mealday/{id}")
	public MealDay createNewEquipment(
			@RequestBody MealDay mealDay, 
			HttpServletResponse response,
			Principal principal,
			@PathVariable int id
			){
		MealDay newMealDay = mdServ.createMealDay(mealDay, principal.getName(), id);
		if (newMealDay != null) {
			return newMealDay;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("mealday/{id}")
	public MealDay updateExistingEquipment(
			@RequestBody MealDay mealDay, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		MealDay editMealDay = mdServ.updateMealDay(id, mealDay, principal.getName());
		if (editMealDay != null) {
			return editMealDay;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("mealday/{id}")
	public void deleteMealDay(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
		try {
			deleted = mdServ.deleteMealDay(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
