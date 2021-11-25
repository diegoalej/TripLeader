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

public class MealDayTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private MealDay mealday;

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
		mealday = em.find(MealDay.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mealday = null;
	}

	@Test
	void test() {
		assertNotNull(mealday);
		assertEquals("Limestone Campsite", mealday.getSleep());
		assertEquals(null, mealday.getDescription());
	}
	
	@Test
	void relationTest() {
		assertEquals("Gates of Lodore", mealday.getTrip().getName());
		assertEquals(3, mealday.getMeals().size());
		
	}

}
