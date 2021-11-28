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

import com.downstreammedia.sandbar.entities.Category;
import com.downstreammedia.sandbar.services.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class CategoryController {

	@Autowired
	CategoryService catServ;
	
	@GetMapping("category")
	private List<Category> getAllCategories(HttpServletResponse response){
		List<Category> category = catServ.findAllCategories();
		if (category.size() > 0) {
			return category;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("category/id/{id}")
	public Category getCategoryWithId(@PathVariable Integer id, HttpServletResponse response){
		Category category = catServ.findCategoryById(id);
		if (category != null) {
			return category;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("category/trip/{id}")
	public List<Category> getCategoryWithTripId(@PathVariable int id, HttpServletResponse response){
		List<Category> category = catServ.findCategoryByTripId(id);
		if (category != null) {
			return category;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("category/{id}")
	public Category createNewCategory (
			@RequestBody Category category, 
			@PathVariable int id,
			HttpServletResponse response,
			Principal principal
			){
		Category newCategory = catServ.createCategory(category, principal.getName(), id);
		if (newCategory != null) {
			return newCategory;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("category/{id}")
	public Category updateExistingCategory(
			@RequestBody Category category, 
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		Category editLocation = catServ.updateCategory(id, category, principal.getName());
		if (editLocation != null) {
			return editLocation;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("category/{id}")
	public void deleteCategory(
			@PathVariable int id, 
			HttpServletResponse response,
			Principal principal
			){
		boolean deleted = false;
		try {
			deleted = catServ.deleteCategory(id, principal.getName());
			if (deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {			
			response.setStatus(404);
		}
	}
	
}
