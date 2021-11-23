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

public class FoodTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Food food;

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
		food = em.find(Food.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		food = null;
	}

	@Test
	void test() {
		assertNotNull(food);
		assertEquals("potatoes", food.getName());
	}
	
	@Test
	void relationTest() {
		assertEquals("Locro de papa", food.getMeal().getName());
		
	}

}
