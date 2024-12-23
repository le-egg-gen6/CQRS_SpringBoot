package com.myproject.productqueryservice.dto;

import com.myproject.productqueryservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 4:56 PM Mon 12/23/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private String name;

	private String description;

	private double price;

	private int quantity;

	public ProductDTO(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.quantity = product.getQuantity();
	}

}
