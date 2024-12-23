package com.myproject.productqueryservice.service;

import com.myproject.productqueryservice.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:24 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class EventListenerService {

	private final ProductQueryService productQueryService;

	@EventListener
	public void handleProductEvent(ProductEvent productEvent) {
		System.out.println(productEvent);
	}

}
