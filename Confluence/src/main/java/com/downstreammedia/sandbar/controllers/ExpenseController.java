package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downstreammedia.sandbar.entities.Expense;
import com.downstreammedia.sandbar.services.ExpenseService;
import com.downstreammedia.sandbar.services.TripService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 4220
public class ExpenseController {

	
	@Autowired
	ExpenseService exServ;

	@Autowired
	TripService tripServ;
	
	@GetMapping("expenses")
	List<Expense> getAllExpenses(HttpServletResponse response){
		List<Expense> expenses = exServ.findAll();
		if(expenses.size() > 0) {
			return expenses;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("expenses/creator/{id}")
	Set<Expense> findExpenseByCreatorId(@PathVariable Integer id, HttpServletResponse response) {
		Set<Expense> expense = exServ.findExpenseByCreatorId(id);
		if(expense.size() > 0) {
			return expense;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("expenses/trip/{id}")
	Set<Expense> findExpenseByTripId(@PathVariable Integer id, HttpServletResponse response) {
		Set<Expense> expense = exServ.findExpenseByTripId(id);
		if(expense.size() > 0) {
			return expense;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@GetMapping("expenses/id/{id}")
	Expense findExpenseById(@PathVariable Integer id, HttpServletResponse response) {
		Expense expense = exServ.findExpenseById(id);
		if(expense != null) {
			return expense;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}

	@PostMapping("expenses/trip/{tripId}")
	Expense createExpense(@RequestBody Expense expense,
						@PathVariable Integer tripId, 
						HttpServletResponse response,
						Principal principal
						) {
		Expense newExpense = exServ.createExpense(expense, principal.getName(), tripId);
		if(newExpense != null) {
			return newExpense;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("expenses/{id}")
	Expense updateExpense(@PathVariable Integer id,
							Principal principal, 
							HttpServletResponse response,
							@RequestBody Expense expense) {
		Expense editExpense = exServ.updateExpense(expense, principal.getName(), id);
		if(editExpense != null) {
			return editExpense;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("expense/{id}")
	public void deleteExpense(@PathVariable Integer id,
							Principal principal,
							HttpServletResponse response) {
		boolean deleted = false;
		try {
			deleted = exServ.deleteExpense(id, principal.getName());
			if(deleted == true) {
				response.setStatus(204);
			}
		} catch (Exception e) {
			response.setStatus(404);
		}
	}
	
}
