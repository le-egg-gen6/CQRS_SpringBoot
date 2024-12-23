package com.myproject.productqueryservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 4:19 PM Mon 12/23/2024
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

	private final ApplicationEventPublisher eventPublisher;


}
