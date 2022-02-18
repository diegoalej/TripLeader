package com.downstreammedia.sandbar.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.downstreammedia.sandbar.entities.Expense;

/**
 * Interface is JPA repository for Expense class
 * 
 * @author Diego Hoyos
 */
public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
	
	Set<Expense> findByCreator_Id(int id);
	
	Set<Expense> findByTrip_Id(int id);

}
