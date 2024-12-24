package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.entity.Product;
import com.myproject.productcommandservice.entity.ProductEventOutbox;
import com.myproject.productcommandservice.event.ProductEvent;
import com.myproject.productcommandservice.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:32 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

	@Value("${kafka.topic}")
	private String topic;

	private final ProductEventOutboxService productEventOutboxService;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Transactional
	public void publishEvent(Object eventObj) {
		if (!(eventObj instanceof ProductEventOutbox outbox)) {
			return;
		}
		String productJson = outbox.getProductJson();
		Product product = JsonUtils.fromJsonString(productJson, Product.class);
		ProductEvent event = new ProductEvent(outbox.getEventType(), product);
		kafkaTemplate.send(topic, event)
			.thenAcceptAsync(result -> {
				RecordMetadata metadata = result.getRecordMetadata();
				log.info("Successfully published event to topic {} partition {} offset {}",
					metadata.topic(),
					metadata.partition(),
					metadata.offset()
				);
				productEventOutboxService.processOutboxMessage(outbox.getId());
			})
			.exceptionally(ex -> {
				log.error("Failed to publish event to Kafka for product ID: {}",
					product.getId(),
					ex
				);
				return null;
			});
	}

}
