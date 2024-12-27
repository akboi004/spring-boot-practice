package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;

@Controller
public class FlamesController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/calculate")
	public String calculateFlames(@RequestParam(value = "name1", required = false) String name1,
			@RequestParam(value = "name2", required = false) String name2, Model model) {

		if (name1 == null || name1.trim().isEmpty() || name2 == null || name2.trim().isEmpty()) {
			model.addAttribute("error", "Please enter both names.");
			return "index"; // Return to the input form with the error message
		}

		if (name1.equals(name2)) {
			model.addAttribute("error", "Please enter two different names.");
			return "index";
		}

		String result = calculateFlamesResult(name1, name2);
		model.addAttribute("name1", name1);
		model.addAttribute("name2", name2);
		model.addAttribute("flamesResult", result);
		return "result";
	}

	private String calculateFlamesResult(String name1, String name2) {
		name1 = name1.replaceAll("\\s+", "").toLowerCase();
		name2 = name2.replaceAll("\\s+", "").toLowerCase();

		// Using StringUtils from Apache Commons Lang for Levenshtein distance
		int commonLetters = StringUtils.getLevenshteinDistance(name1, name2);

		List<Character> flamesList = new ArrayList<>(Arrays.asList('F', 'L', 'A', 'M', 'E', 'S'));

		int currentIndex = 0;
		while (flamesList.size() > 1) {
			// Calculate the index to remove
			currentIndex = (currentIndex + commonLetters - 1) % flamesList.size();

			// Remove the element at the calculated index
			flamesList.remove(currentIndex);

			// Adjust the currentIndex if it goes out of bounds
			if (currentIndex == flamesList.size()) {
				currentIndex = 0; // Reset the index to 0 after removal
			}
		}

		return String.valueOf(flamesList.get(0)); // Return the remaining character as the result
	}
}
