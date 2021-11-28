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

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired 
	ExpenseRepository exRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	TripRepository tripRepo;

	@Override
	public List<Expense> findAll() {
		return exRepo.findAll();
	}

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

	@Override
	public Expense createExpense(Expense expense, String username, int id) {
		Expense newExpense = null;
		User user = userRepo.findByUsername(username);
		Optional<Trip> trip = tripRepo.findById(id);
		if (user != null && trip.isPresent()) {
			expense.setTrip(trip.get());
			expense.setCreator(user);
			newExpense = exRepo.saveAndFlush(expense);
		}
		return newExpense;	
	}

	@Override
	public Expense updateExpense(Expense expense, String username, int id) {
		Optional<Expense> oldExpense = exRepo.findById(id);
		Expense managedExpense = null;
		User user = userRepo.findByUsername(username);
		if (oldExpense.isPresent()) {
			managedExpense = oldExpense.get();
			managedExpense.setName(expense.getName());
			managedExpense.setDate(expense.getDate());
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
