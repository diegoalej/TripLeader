package com.downstreammedia.sandbar.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpenseTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Expense expense;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SandBar");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		expense = em.find(Expense.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		expense = null;
	}

	@Test
	void test() {
		assertNotNull(expense);
		assertEquals("Gas to putin", expense.getName());
	}
	
	@Test
	void relationTest() {
		assertEquals("firstUser", expense.getCreator().getUsername());
		assertEquals("Gates of Lodore", expense.getTrip().getName());
	}

}
