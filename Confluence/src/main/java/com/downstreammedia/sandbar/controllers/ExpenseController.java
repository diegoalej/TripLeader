package com.downstreammedia.sandbar.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.downstreammedia.sandbar.exception.ResourceNotDeletedException;
import com.downstreammedia.sandbar.exception.ResourceNotFoundException;
import com.downstreammedia.sandbar.exception.ResourceNotUpdatedException;
import com.downstreammedia.sandbar.services.ExpenseService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 
public class ExpenseController {

	
	@Autowired
	ExpenseService exServ;

	
	@GetMapping("expenses")
	ResponseEntity<List<Expense>> getAllExpenses(){
		List<Expense> expenses = exServ.findAll();
		if(expenses.size() > 0) {
			return new ResponseEntity<List<Expense>>(expenses, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(0, "No Expenses found");
		}
	}
	
	@GetMapping("expenses/creator/{id}")
	ResponseEntity<Set<Expense>> findExpenseByCreatorId(@PathVariable Integer id) {
		Set<Expense> expense = exServ.findExpenseByCreatorId(id);
		if(expense.size() > 0) {
			return new ResponseEntity<Set<Expense>>(expense, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Could not find expense with that user id");
		}
	}

	@GetMapping("expenses/trip/{id}")
	ResponseEntity<Set<Expense>> findExpenseByTripId(@PathVariable Integer id) {
		Set<Expense> expense = exServ.findExpenseByTripId(id);
		if(expense.size() > 0) {
			return new ResponseEntity<Set<Expense>>(expense, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Could not find expense with that trip id");
		}
	}

	@GetMapping("expenses/id/{id}")
	ResponseEntity<Expense> findExpenseById(@PathVariable Integer id) {
		Expense expense = exServ.findExpenseById(id);
		if(expense != null) {
			return new ResponseEntity<Expense>(expense, HttpStatus.OK);
		}
		else {
			throw new ResourceNotFoundException(id, "Could not find expense with that id");
		}
	}

	@PostMapping("expenses/trip/{tripId}")
	ResponseEntity<Expense> createExpense(@RequestBody Expense expense,
						@PathVariable Integer tripId, 
						Principal principal) {
		Expense newExpense = exServ.createExpense(expense, principal.getName(), tripId);
		if(newExpense != null) {
			return new ResponseEntity<Expense>(expense, HttpStatus.CREATED);
		}
		else {
			throw new ResourceNotUpdatedException(0, "Could not create expense");
		}
	}
	
	@PutMapping("expenses/{id}")
	ResponseEntity<Expense> updateExpense(@PathVariable Integer id,
							Principal principal, 
							@RequestBody Expense expense) {
		Expense expenseExists = exServ.findExpenseById(id);
		if(expenseExists!=null) {			
			Expense editExpense = exServ.updateExpense(expense, principal.getName(), id);
			if(editExpense != null) {
				return new ResponseEntity<Expense>(editExpense, HttpStatus.OK);
			}
			else {
				throw new ResourceNotUpdatedException(id, "Expense could not be updated");
			}
		}
		throw new ResourceNotFoundException(id, "Expense does not exist in database");
	}
	
	@DeleteMapping("expense/{id}")
	public ResponseEntity<Object> deleteExpense(@PathVariable Integer id,Principal principal) {
		boolean deleted = false;
		deleted = exServ.deleteExpense(id, principal.getName());
		if(deleted == true) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		throw new ResourceNotDeletedException(id, "Expense could not be deleted");	
	}
	
}
