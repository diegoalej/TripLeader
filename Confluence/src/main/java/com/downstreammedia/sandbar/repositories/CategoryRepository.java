package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
