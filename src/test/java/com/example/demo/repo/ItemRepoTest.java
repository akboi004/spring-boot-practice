package com.example.demo.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.entity.Item;

@DataJpaTest
public class ItemRepoTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private ItemRepo repo;

	@Test
	void verifyBootstrappingByPersistingAnItem() {
		Item item = new Item();
		item.setName("Samsung");
		item.setPrice(27234.455);

		Assertions.assertNull(item.getId());
		em.persist(item);
		Assertions.assertNotNull(item.getId());

	}

	@Test
	void verifyRepositoryByPersistingAnItem() {
		Item item = new Item();
		item.setName("Apple");
		item.setPrice(6323.99);

		Assertions.assertNull(item.getId());
		repo.save(item);
		Assertions.assertNotNull(item.getId());
	}
}
