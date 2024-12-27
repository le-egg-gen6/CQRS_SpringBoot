package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.dto.ProductDTO;
import com.myproject.productcommandservice.entity.Product;
import com.myproject.productcommandservice.entity.ProductEventOutbox;
import com.myproject.productcommandservice.repository.ProductEventOutboxRepository;
import com.myproject.productcommandservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:32 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class ProductCommandService {

	private final ProductRepository productRepository;

	private final ProductEventOutboxRepository productEventOutboxRepository;

	@Transactional(rollbackFor = Exception.class)
	public Product createProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product = productRepository.save(product);

		ProductEventOutbox outboxMessage = new ProductEventOutbox("CREATE", product);
		productEventOutboxRepository.save(outboxMessage);

		return product;
	}

	@Transactional(rollbackFor = Exception.class)
	public Product updateProduct(String id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			product.setName(productDTO.getName());
			product.setDescription(productDTO.getDescription());
			product.setPrice(productDTO.getPrice());
			product.setQuantity(productDTO.getQuantity());
			product = productRepository.save(product);

			ProductEventOutbox outboxMessage = new ProductEventOutbox("UPDATE", product);
			productEventOutboxRepository.save(outboxMessage);
		}
		return product;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deleteProduct(String id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			productRepository.delete(product);

			ProductEventOutbox outboxMessage = new ProductEventOutbox("DELETE", product);
			productEventOutboxRepository.save(outboxMessage);
		}
	}

}
