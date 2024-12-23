package com.myproject.productcommandservice.controller;

import com.myproject.productcommandservice.dto.ProductDTO;
import com.myproject.productcommandservice.service.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 4:48 PM Mon 12/23/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductCommandController {

	private final ProductCommandService commandService;

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok(commandService.createProduct(productDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDTO productEvent) {
		return ResponseEntity.ok(commandService.updateProduct(id, productEvent));
	}

}
