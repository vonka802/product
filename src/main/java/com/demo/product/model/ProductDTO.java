package com.demo.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data // Generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor
public class ProductDTO {
	@Getter @Setter private Long id;
	@Getter @Setter   private String name;
	@Getter @Setter    private String description;
	@Getter @Setter    private double price;
}
