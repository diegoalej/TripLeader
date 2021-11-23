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

public class MealTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Meal meal;

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
		meal = em.find(Meal.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		meal = null;
	}

	@Test
	void test() {
		assertNotNull(meal);
		assertEquals("Locro de papa", meal.getName());
		assertEquals("Ecuadorian potato soup with spinach", meal.getDescription());
	}
	
	@Test
	void relationTest() {
		assertEquals("firstUser", meal.getCreator().getUsername());
		assertEquals("Limestone Campsite", meal.getDayserved().getSleep());
		
	}
}
