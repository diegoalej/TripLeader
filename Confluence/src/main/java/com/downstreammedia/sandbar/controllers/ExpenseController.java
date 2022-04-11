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

/**
 * Class is REST controller for Expense 
 * 
 * @author Diego Hoyos
 */
@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})//Angular local port 
public class ExpenseController {

	
	@Autowired
	ExpenseService exServ;

	/**
	 * Endpoint returns all Expenses or null
	 * 
	 * @return - a list of all Expense
	 * @throws - ResourceNotFoundException
	 */
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
	
	/**
	 * Endpoint returns Expense with specific creator id value
	 * 
	 * @param id - creator id owner of expenses
	 * @return - a Expense object
	 * @throws - ResourceNotFoundException
	 */
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
	
	/**
	 * Endpoint returns Expense with specific trip id value
	 * 
	 * @param id - Trip id for expense to be found
	 * @return - a Expense object
	 * @throws - ResourceNotFoundException
	 */
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
	
	/**
	 * Endpoint returns Expense with specific  id value
	 * 
	 * @param id - Expense id to be found
	 * @return - a Expense object
	 * @throws - ResourceNotFoundException
	 */
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
	
	/**
	 * Endpoint creates new Expense
	 * 
	 * @param expense - Expense object to be created
	 * @param tripId - Trip expense will be added to
	 * @param principal - Owner of expense
	 * @return - a Expense object
	 * @throws - ResourceNotFoundException
	 */
	@PostMapping(value="expenses/trip/{tripId}", consumes="application/json")
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
	
	/**
	 * Endpoint edits existing Expense 
	 * 
	 * @param id - Expense to be updated
	 * @param expense - edited Expense object
	 * @param principal - user performing the edit
	 * @return - a Expense object or null
	 * @throws - ResourceNotUpdatedException
	 * @throws - ResourceNotFoundException
	 */
	@PutMapping(value ="expenses/{id}", consumes="application/json")
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
	
	/**
	 * Endpoint deletes Expense with specific id value
	 * 
	 * @param id - Expense to be deleted
	 * @param principal - user performing the delete
	 * @return - boolean with result
	 * @throws - ResourceNotDeletedException
	 */
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
