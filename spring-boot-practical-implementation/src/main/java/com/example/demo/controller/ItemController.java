package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.errorHandling.ItemNotFoundException;
import com.example.demo.repo.ItemRepo;

import jakarta.validation.Valid;

/*CRUD IMPLEMENTATION*/
@RestController
public class ItemController {

	@Autowired
	ItemRepo itemRepo;

	@GetMapping("/item/{id}")
	Item getById(@PathVariable Long id) {
		Optional<Item> optionalItem = itemRepo.findById(id);
		if (optionalItem.isPresent()) {
			return optionalItem.get();
		} else {
			throw new ItemNotFoundException(id);
		}
	}

	@GetMapping("/getAllItem")
	List<Item> getAllItem() {
		return itemRepo.findAll();
	}

	@PostMapping("/createItem")
	Item createItem(@Valid @RequestBody Item item) {
		return itemRepo.save(item);
	}

	@DeleteMapping("/deleteItem/{id}")
	void deleteItem(@PathVariable Long id) {
		itemRepo.deleteById(id);
	}

	@DeleteMapping("/deleteAll")
	void deleteAll() {
		itemRepo.deleteAll();
	}

	@PutMapping("/updateOrCreateItem/{id}")
	Item updateOrCreateItem(@RequestBody Item item, @PathVariable Long id) {
		Optional<Item> optionalItem = itemRepo.findById(id);
		if (optionalItem.isPresent()) {
			Item existingItem = optionalItem.get();
			existingItem.setName(existingItem.getName());
			return itemRepo.save(existingItem);
		} else {
			item.setId(id);
			return itemRepo.save(item);
		}

	}

}
