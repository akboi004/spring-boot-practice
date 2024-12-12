package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ITEM_DTLS")
public class Item extends RepresentationModel<Item> {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	@Column(name = "name")
	private String name;

	@Min(value = 1, message = "Price must be greater than or equal to 1")
	@Column(name = "price")
	private double price;

}
