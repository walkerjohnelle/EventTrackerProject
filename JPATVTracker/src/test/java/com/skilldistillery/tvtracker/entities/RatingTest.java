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

public class RatingTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rating rating;

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
		rating = em.find(Rating.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Ratings_entity_mapping() {
		assertNotNull(rating);
		assertEquals(10, rating.getRating());

	}
	@Test
	void test_Rating_User_entity_mapping() {
		assertNotNull(rating);
		assertEquals("walkerjohnelle", rating.getUser().getUsername());
		
	}
	@Test
	void test_Rating_TvShow_entity_mapping() {
		assertNotNull(rating);
		assertEquals("The Sapranos", rating.getTvShow().getTitle());
		
	}

}
