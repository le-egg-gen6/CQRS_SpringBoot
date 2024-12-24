package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.entity.ProductEventOutbox;
import com.myproject.productcommandservice.repository.ProductEventOutboxRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 11:41 AM Tue 12/24/2024
 */
@Service
@RequiredArgsConstructor
public class ProductEventOutboxService {

	private final ProductEventOutboxRepository productEventOutboxRepository;

	public List<ProductEventOutbox> getAllUnprocessedOutboxMessages() {
		return productEventOutboxRepository.findAllBySentIsFalse();
	}

	public void processOutboxMessage(String outboxId) {
		ProductEventOutbox outbox = productEventOutboxRepository.findById(outboxId).orElse(null);
		if (outbox != null) {
			outbox.setSent(true);
			productEventOutboxRepository.save(outbox);
		}
	}

}
