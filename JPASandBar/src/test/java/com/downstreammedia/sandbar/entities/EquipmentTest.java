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

public class EquipmentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Equipment equipment;

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
		equipment = em.find(Equipment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		equipment = null;
	}

	@Test
	void test() {
		assertNotNull(equipment);
		assertEquals("Fire pan", equipment.getName());
	}
	
	@Test
	void relationTest() {
		assertEquals(0, equipment.getTrips().size());
		
	}

}
