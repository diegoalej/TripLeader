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

@Service 
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	TripRepository tripRepo;
	
	@Override
	public List<Category> findAllCategories() {
		return catRepo.findAll();
	}
	
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
	
	public List<Category> findCategoryByTripId(int id) {
		List<Category> category = catRepo.findByTrip_Id(id);
		if(category != null) {
			return category;
		}
		else {
			return null;
		}
	}
	
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
