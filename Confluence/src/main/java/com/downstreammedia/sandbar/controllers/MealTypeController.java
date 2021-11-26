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

import com.downstreammedia.sandbar.entities.MealType;
import com.downstreammedia.sandbar.services.MealTypeService;

@RestController
@RequestMapping ("api")
@CrossOrigin({"*", "http://localhost:4220"}) // Angular local port
public class MealTypeController {
	
	@Autowired
	private MealTypeService mtServ;
	
	@GetMapping("mealtypes")
	private List<MealType> getAllMealTypes(HttpServletResponse response){
		List<MealType> mealtype = mtServ.findAllMealTypes();
		if (mealtype.size() > 0) {
			return mealtype;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("mealtypes/{id}")
	public MealType getMealTypeWithId(@PathVariable Integer id, HttpServletResponse response){
		MealType mealtype = mtServ.findMealTypeById(id);
		if (mealtype != null) {
			return mealtype;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	
	@PostMapping("mealtypes")
	public MealType createNewMealType(
			@RequestBody MealType mealtype, 
			HttpServletResponse response
			){
		MealType newMealtype = mtServ.createMealType(mealtype);
		if (newMealtype != null) {
			return newMealtype;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("mealtypes/{id}")
	public MealType updateExistingMealType(
			@RequestBody MealType mealtype, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		MealType editMealType = mtServ.updateMealType(id, mealtype, principal.getName());
		if (editMealType != null) {
			return editMealType;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("mealtypes/{id}")
	public void deleteMealType(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
		try {
			deleted = mtServ.deleteMealType(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}

}
