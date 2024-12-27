package com.myproject.productqueryservice.service;

import com.myproject.productqueryservice.entity.Product;
import com.myproject.productqueryservice.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:19 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class ProductQueryService {

	private final ProductRepository productRepository;

	@Transactional(rollbackFor = Exception.class)
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Transactional(rollbackFor = Exception.class)
	public Product updateProduct(Product product) {
		Product oldProduct = productRepository.findById(product.getId()).orElse(null);
		if (oldProduct != null) {
			oldProduct.setName(product.getName());
			oldProduct.setDescription(product.getDescription());
			oldProduct.setPrice(product.getPrice());
			oldProduct.setQuantity(product.getQuantity());
			product = productRepository.save(oldProduct);
		}
		return product;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProduct(Product product) {
		Product oldProduct = productRepository.findById(product.getId()).orElse(null);
		if (oldProduct != null) {
			productRepository.delete(oldProduct);
		}
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
