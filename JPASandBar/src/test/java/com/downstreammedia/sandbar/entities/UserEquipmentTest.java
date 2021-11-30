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

public class UserEquipmentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserEquipment userE;

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
		userE = em.find(UserEquipment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userE = null;
	}

	@Test
	void test() {
		assertNotNull(userE);
		assertEquals(true, userE.isActive());
	}
	
	@Test
	void relationTest() {
		assertEquals("firstUser", userE.getCreator().getUsername());
		assertEquals("Fire pan", userE.getEquipment().getName());
		assertEquals("Gates of Lodore", userE.getTrip().getName());
		
	}

}
