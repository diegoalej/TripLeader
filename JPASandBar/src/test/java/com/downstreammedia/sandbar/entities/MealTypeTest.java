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

public class MealTypeTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private MealType mealType;

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
		mealType = em.find(MealType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mealType = null;
	}

	@Test
	void test() {
		assertNotNull(mealType);
		assertEquals("breakfast", mealType.getName());
	}

}
