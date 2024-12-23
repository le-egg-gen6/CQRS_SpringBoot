package com.myproject.productcommandservice.service;

import com.myproject.productcommandservice.repository.ProductEventOutboxRepository;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 4:32 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

	@Value("${kafka.topic}")
	private String topic;

	private final ProductEventOutboxRepository productEventOutboxRepository;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Transactional
	public void publishEvent(Object eventObj) {
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, eventObj);

		future.whenComplete((result, ex) -> {
			if (ex != null) {

			} else {

			}
		});

	}

}
