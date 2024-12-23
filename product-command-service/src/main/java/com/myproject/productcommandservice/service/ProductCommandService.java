package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.dto.ProductDTO;
import com.myproject.productcommandservice.entity.Product;
import com.myproject.productcommandservice.repository.ProductEventOutboxRepository;
import com.myproject.productcommandservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:32 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class ProductCommandService {

	private final ProductRepository productRepository;

	private final ProductEventOutboxRepository productEventOutboxRepository;

	private final KafkaProducerService kafkaProducerService;

	public Product createProduct(ProductDTO productDTO) {
		return null;
	}

	public Product updateProduct(String id, ProductDTO productDTO) {
		return null;
	}

}
