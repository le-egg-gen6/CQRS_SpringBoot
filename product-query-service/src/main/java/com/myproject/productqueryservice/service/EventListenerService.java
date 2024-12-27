package com.myproject.productqueryservice.service;

import com.myproject.productqueryservice.entity.Product;
import com.myproject.productqueryservice.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author nguyenle
 * @since 4:24 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class EventListenerService {

	private final ProductQueryService productQueryService;

	@TransactionalEventListener
	public void handleProductEvent(ProductEvent productEvent) {
		if (productEvent.getSource() instanceof Product product) {
			switch (productEvent.getType()) {
				case "CREATE":
					productQueryService.createProduct(product);
					break;
				case "UPDATE":
					productQueryService.updateProduct(product);
					break;
				case "DELETE":
					productQueryService.deleteProduct(product);
					break;
				default:
					break;
			}
		}
	}

}
