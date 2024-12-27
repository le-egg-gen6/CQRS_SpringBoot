package com.myproject.productqueryservice.service;

import com.myproject.productqueryservice.entity.Product;
import com.myproject.productqueryservice.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:19 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

	private final ApplicationEventPublisher eventPublisher;

	@KafkaListener(
		topics = "${kafka.topic}",
		groupId = "${spring.kafka.consumer.group-id}",
		containerFactory = "kafkaListenerContainerFactory"

	)
	public void consumeProductEvent(
		@Payload ProductEvent event,
		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
		@Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
		@Header(KafkaHeaders.OFFSET) Long offset
	) {
		try {
			log.info(
				"Received product event from topic: {}, partition: {}, offset: {}",
				topic,
				partition,
				offset
			);
			Product product;
			if (event.getSource() instanceof Product) {
				product = (Product) event.getSource();

				log.info(
					"Processing product event type: {} for product ID: {}",
					event.getType(),
					product.getId()
				);

				eventPublisher.publishEvent(event);

				log.info(
					"Successfully processed product event for product ID: {}",
					product.getId()
				);
			}
		} catch (Exception exception) {
			log.error(
				"Error processing product event from topic: {}, partition: {}, offset: {}",
				topic,
				partition,
				offset,
				exception
			);
		}
	}
}
