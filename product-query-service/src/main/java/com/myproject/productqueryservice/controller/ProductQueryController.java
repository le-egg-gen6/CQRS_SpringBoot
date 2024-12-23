package com.myproject.productqueryservice.controller;

import com.myproject.productqueryservice.dto.ProductDTO;
import com.myproject.productqueryservice.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 4:54 PM Mon 12/23/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductQueryController {

	private final ProductQueryService queryService;

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.ok(queryService.getAllProducts().stream().map(ProductDTO::new).toList());
	}
}
