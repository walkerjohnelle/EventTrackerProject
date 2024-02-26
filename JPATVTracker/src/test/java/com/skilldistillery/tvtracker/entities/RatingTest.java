package com.skilldistillery.jobtracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SkillsTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Skill skill;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAJobTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		skill = em.find(Skill.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	void test_Skill_entity_mapping() {
		assertNotNull(skill);
		assertEquals("Python", skill.getName());

	}
	@Test
	void test_Skill_Job_entity_mapping() {
		assertNotNull(skill);
		assertTrue(skill.getJobs().size() == 1);
		assertFalse(skill.getJobs().size() > 1);
		
	}
	@Test
	void test_Skill_User_entity_mapping() {
		assertNotNull(skill);
		assertTrue(skill.getUsers().size() == 1);
		
	}

}
