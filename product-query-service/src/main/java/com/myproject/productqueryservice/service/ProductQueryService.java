package com.myproject.productqueryservice.service;

import com.myproject.productqueryservice.entity.Product;
import com.myproject.productqueryservice.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:19 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class ProductQueryService {

	private final ProductRepository productRepository;

	public Product createProduct(Product product) {
		return null;
	}

	public Product updateProduct(Product product) {
		return null;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
