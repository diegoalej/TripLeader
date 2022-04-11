package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.CategoryService;

/**
 * Class is REST controller for Category 
 * 
 * @author Diego Hoyos
 */
@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })//Angular local port 4220
public class CategoryController {

	@Autowired
	CategoryService catServ;
	
	/**
	 * Endpoint returns all Category or null
	 * 
	 * @return - a list of all Category
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("category")
	private ResponseEntity<List<Category>> getAllCategories(){
		List<Category> category = catServ.findAllCategories();
		if (category.size() > 0) {
			return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No Category found");
		}
	}
	
	/**
	 * Endpoint returns Category with specific id value
	 * 
	 * @param id - Category id to be found
	 * @return - a Category object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("category/id/{id}")
	public ResponseEntity<Category> getCategoryWithId(@PathVariable Integer id){
		Category category = catServ.findCategoryById(id);
		if (category != null) {
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Category does not exist with that id");
		}
	}
	
	/**
	 * Endpoint returns Location with specific trip id value
	 * 
	 * @param id - Trip id owner of category
	 * @return - a Category object
	 * @throws - ResourceNotFoundException
	 */
	@GetMapping("category/trip/{id}")
	public ResponseEntity<List<Category>> getCategoryWithTripId(@PathVariable int id){
		List<Category> category = catServ.findCategoryByTripId(id);
		if (category != null) {
			return new ResponseEntity<List<Category>>(category, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Trip does not have any categories assigned");
		}
	}
	
	/**
	 * Endpoint creates new Category
	 * 
	 * @param category - Category object to be created
	 * @param principal - user making the edit
	 * @return - a Category object
	 * @throws - ResourceNotFoundException
	 */
	@PostMapping(value = "category/{id}", consumes="application/json")
	public ResponseEntity<Category> createNewCategory (
			@RequestBody Category category, 
			@PathVariable int id,
			Principal principal
			){
		Category newCategory = catServ.createCategory(category, principal.getName(), id);
		if (newCategory != null) {
			return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotUpdatedException(id, "Category could not be created");
		}
	}
	
	/**
	 * Endpoint edits existing Category 
	 * 
	 * @param id - Category to be updated
	 * @param category - edited Category object
	 * @param principal - user performing the edit
	 * @return - a Category object or null
	 * @throws - ResourceNotUpdatedException
	 * @throws - ResourceNotFoundException
	 */
	@PutMapping(value = "category/{id}", consumes="application.json")
	public ResponseEntity<Category> updateExistingCategory(
			@RequestBody Category category, 
			@PathVariable int id, 
			Principal principal
			){
		Category categoryExists = catServ.findCategoryById(id);
		if(categoryExists != null) {
			Category editCategory = catServ.updateCategory(id, category, principal.getName());
			if (editCategory != null) {
				return new ResponseEntity<Category>(editCategory, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "Category could not be updated");
			}
		}
		else {
			throw new ResourceNotFoundException(id, "Category does not exist in database");
		}
	}
	
	/**
	 * Endpoint deletes Category with specific id value
	 * 
	 * @param id - Category to be deleted
	 * @param principal - user performing the delete
	 * @return - boolean with result
	 * @throws - ResourceNotDeletedException
	 */
	@DeleteMapping("category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable int id, Principal principal){
		boolean deleted = false;
		deleted = catServ.deleteCategory(id, principal.getName());
		if (deleted == true) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
		throw new ResourceNotDeletedException(id, "Category could not be deleted");
	}
	
}
