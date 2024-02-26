package com.skilldistillery.tvtracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPATvTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("johnellewalker@gmail.com", user.getEmail());

	}
	@Test
	void test_User_TvShow_mapping() {
		assertNotNull(user);
		assertEquals("Atlanta", user.getShows().get(1).getTitle());
		
	}
	@Test
	void test_User_Rating_mapping() {
		assertNotNull(user);
		assertEquals(10, user.getRatings().get(0).getRating());
		
	}


}
