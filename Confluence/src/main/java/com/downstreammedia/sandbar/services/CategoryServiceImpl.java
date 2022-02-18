package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Category;
import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.repositories.CategoryRepository;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements CategoryService and defines business logic
 * for manipulating Category entity
 * 
 * @author Diego Hoyos
 */
@Service 
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	TripRepository tripRepo;
	
	/**
	 * Method returns all Category or null
	 * 
	 * @return - a list of all Category
	 */
	@Override
	public List<Category> findAllCategories() {
		return catRepo.findAll();
	}
	
	/**
	 * Method returns Category with specific id value
	 * 
	 * @param id - Category to be found
	 * @return - a Category object
	 */
	@Override
	public Category findCategoryById(int id) {
		Optional<Category> category = catRepo.findById(id);
		if(category.isPresent()) {
			return category.get();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method returns Category with specific id value
	 * 
	 * @param id - Category to be found
	 * @return - a Category object or null
	 */
	@Override
	public List<Category> findCategoryByTripId(int id) {
		List<Category> category = catRepo.findByTrip_Id(id);
		if(category != null) {
			return category;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Method edits existing Category 
	 * 
	 * @param id - Category to be updated
	 * @param user - edited Category object
	 * @param username - user performing the edit
	 * @return - a Category object or null
	 */
	@Override
	public Category updateCategory(int id, Category trip, String username) {
		Optional<Category> oldCategory = catRepo.findById(id);
		Category managedCategory = null;
		if (oldCategory.isPresent()) {
			managedCategory = oldCategory.get();
			managedCategory.setId(id);
			managedCategory.setName(trip.getName());
			managedCategory.setDescription(trip.getDescription());
			managedCategory.setActive(trip.isActive());
			if (userRepo.findByUsername(username) != null) {
				return catRepo.saveAndFlush(managedCategory);
			}			
		}
		return null;
	}
	
	/**
	 * Method creates a Category 
	 * 
	 * @param category - Category object to be created
	 * @param username - user performing the edit
	 * @return - a Category object or null
	 */
	@Override
	public Category createCategory(Category category, String username, int id) {
		Category newCategory = null;
		Optional<Trip> trip = tripRepo.findById(id);
		if (trip.isPresent()) {
			category.setTrip(trip.get());
			newCategory = catRepo.saveAndFlush(category);
		}
		return newCategory;			
	}
	
	/**
	 * Method deletes Category with specific id value
	 * 
	 * @param id - Category to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteCategory(int id, String username) {
		boolean answer = false;
		Optional<Category> category = catRepo.findById(id);
		if (category.isPresent() && userRepo.findByUsername(username).getRole().equals("admin")) {
			catRepo.deleteById(id);
			answer = true;
		}	
		return answer;
	}

}
