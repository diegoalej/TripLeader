package com.downstreammedia.sandbar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer>{

}
