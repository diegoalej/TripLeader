package com.downstreammedia.sandbar.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.downstreammedia.sandbar.entities.Expense;
import com.downstreammedia.sandbar.entities.Trip;
import com.downstreammedia.sandbar.entities.User;
import com.downstreammedia.sandbar.repositories.ExpenseRepository;
import com.downstreammedia.sandbar.repositories.TripRepository;
import com.downstreammedia.sandbar.repositories.UserRepository;

/**
 * Class implements ExpenseService and defines business 
 * logic for manipulating Expense entity
 * 
 * @author Diego Hoyos
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired 
	ExpenseRepository exRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	TripRepository tripRepo;
	
	/**
	 * Method returns all Expense or null
	 * 
	 * @return - a list of all Expenses
	 */
	@Override
	public List<Expense> findAll() {
		return exRepo.findAll();
	}
	
	/**
	 * Method returns Expense with specific id value
	 * 
	 * @param id - Expense to be found
	 * @return - a Expense object
	 */
	@Override
	public Expense findExpenseById(int id) {
		Optional<Expense> expense = exRepo.findById(id);
		if(expense.isPresent()) {
			return expense.get();
		}
		else {			
			return null;
		}
	}
	
	/**
	 * Method returns Expense with specific creator id value
	 * 
	 * @param id - user who created expense to be found
	 * @return - a Expense object
	 */
	@Override
	public Set<Expense> findExpenseByCreatorId(int id) {
		Set<Expense> expense = exRepo.findByCreator_Id(id);
		if(expense != null) {
				return expense;
			}
			else {			
				return null;
			}
	}
	
	/**
	 * Method returns user with specific trip id value
	 * 
	 * @param id - trip expense belongs to
	 * @return - a Expense object
	 */
	@Override
	public Set<Expense> findExpenseByTripId(int id) {
		Set<Expense> expense = exRepo.findByTrip_Id(id);
		if(expense != null) {
				return expense;
			}
			else {			
				return null;
			}
	}
	
	/**
	 * Method creates a new Expense 
	 * 
	 * @param expense - Expense object to be created
	 * @param username - user creating Expense
	 * @param id - Trip Expense belongs to
	 * @return - created Expense object or null
	 */
	@Override
	public Expense createExpense(Expense expense, String username, int id) {
		User user = userRepo.findByUsername(username);
		Optional<Trip> trip = tripRepo.findById(id);
		if (user != null && trip.isPresent()) {
			expense.setTrip(trip.get());
			expense.setCreator(user);
			Expense newExpense= exRepo.saveAndFlush(expense);
			if(newExpense != null) {
			return newExpense;
			}
			else {
			return null;
			}
		}
		else {
			return null;
		}
	}	
	
	/**
	 * Method edits existing Expense 
	 * 
	 * @param id - Expense to be updated
	 * @param expense - edited Expense object
	 * @param username - user performing the edit
	 * @return - a Expense object or null
	 */
	@Override
	public Expense updateExpense(Expense expense, String username, int id) {
		Optional<Expense> oldExpense = exRepo.findById(id);
		Expense managedExpense = null;
		User user = userRepo.findByUsername(username);
		if (oldExpense.isPresent()) {
			managedExpense = oldExpense.get();
			managedExpense.setName(expense.getName());
			managedExpense.setDate(expense.getDate());
			managedExpense.setDescription(expense.getDescription());
			managedExpense.setCost(expense.getCost());
			managedExpense.setCreator(user);
			if (user != null 
					//Allowing only creator or admin to edit
					//&& user.getId() == oldExpense.get().getCreator().getId()
					) {
				return exRepo.saveAndFlush(managedExpense);
			}
		}
		return managedExpense;
	}
	
	/**
	 * Method deletes Expense with specific id value
	 * 
	 * @param id - Expense to be deleted
	 * @param username - user performing the delete
	 * @return - boolean with result
	 */
	@Override
	public boolean deleteExpense(int id, String username) {
		boolean answer = false;
		Optional<Expense> expense = exRepo.findById(id);
		if(expense.isPresent() && 
				userRepo.findByUsername(username).getRole().equals("admin")) {
			exRepo.deleteById(id);
			answer = true;
		}
		return answer;
	}
	
}
