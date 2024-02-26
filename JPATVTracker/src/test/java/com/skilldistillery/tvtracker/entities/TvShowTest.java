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

public class TvShowTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TvShow show;

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
		show = em.find(TvShow.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_TvShow_entity_mapping() {
		assertNotNull(show);
		assertEquals("The Sapranos", show.getTitle());

	}

	@Test
	void test_TvShow_User_mapping() {
		assertNotNull(show);
		assertEquals("walkerjohnelle", show.getUsers().get(0).getUsername());
		
	}
	
	@Test
	void test_TvShow_Rating_mapping() {
		assertNotNull(show);
		assertEquals(10, show.getRatings().get(0).getRating());
		
	}
}
