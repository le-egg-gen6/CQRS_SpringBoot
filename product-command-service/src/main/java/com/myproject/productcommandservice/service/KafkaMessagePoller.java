package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.entity.ProductEventOutbox;
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

	private final ProductEventOutboxService productEventOutboxService;

	@Scheduled(fixedRate = 5000)
	private void pollingMessages() {
		List<ProductEventOutbox> unprocessedOutboxMessages = productEventOutboxService.getAllUnprocessedOutboxMessages();
		for (ProductEventOutbox outbox : unprocessedOutboxMessages) {
			kafkaProducerService.publishEvent(outbox);
		}
	}

}
