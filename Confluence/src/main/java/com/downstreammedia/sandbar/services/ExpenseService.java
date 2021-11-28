package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Set;

import com.downstreammedia.sandbar.entities.Expense;

public interface ExpenseService {
	
	List<Expense> findAll();
	
	Expense findExpenseById(int id);
	
	Set<Expense> findExpenseByCreatorId(int id);

	Set<Expense> findExpenseByTripId(int id);
	
	Expense createExpense(Expense expense, String username, int id);
	
	Expense updateExpense(Expense expense, String username, int id);
	
	boolean deleteExpense(int id, String username);


}
