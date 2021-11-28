package com.downstreammedia.sandbar.services;

import java.util.List;

import com.downstreammedia.sandbar.entities.Category;

public interface CategoryService {
	
	List<Category> findAllCategories();

	Category findCategoryById(int id);
	
	List<Category> findCategoryByTripId(int id);

	Category createCategory(Category category, String username, int id);

	Category updateCategory(int id, Category category, String username);

	boolean deleteCategory(int id, String username);

}
