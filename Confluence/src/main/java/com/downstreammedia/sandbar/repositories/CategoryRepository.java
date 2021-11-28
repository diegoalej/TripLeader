package com.downstreammedia.sandbar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	List<Category> findByTrip_Id(int id);

}
