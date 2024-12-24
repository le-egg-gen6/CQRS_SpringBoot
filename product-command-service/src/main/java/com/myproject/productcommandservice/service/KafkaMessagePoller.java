package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.entity.Product;
import com.myproject.productcommandservice.entity.ProductEventOutbox;
import com.myproject.productcommandservice.event.ProductEvent;
import com.myproject.productcommandservice.repository.ProductEventOutboxRepository;
import com.myproject.productcommandservice.utils.JsonUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 10:07 AM Tue 12/24/2024
 */
@Service
@RequiredArgsConstructor
public class KafkaMessagePoller {

	private final KafkaProducerService kafkaProducerService;

	private final ProductEventOutboxRepository productEventOutboxRepository;

	@Scheduled(fixedRate = 5000)
	private void pollingMessages() {
		List<ProductEventOutbox> unprocessedOutboxMessages = productEventOutboxRepository.findAllBySentIsFalse();
		for (ProductEventOutbox outbox : unprocessedOutboxMessages) {
			String productJson = outbox.getProductJson();
			Product product = JsonUtils.fromJsonString(productJson, Product.class);
			ProductEvent event = new ProductEvent(outbox.getEventType(), product);
			kafkaProducerService.publishEvent(event);
		}
	}

}
