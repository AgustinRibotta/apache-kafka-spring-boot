package com.appsdeveloperblog.ws.products;

import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	
	@Bean
	NewTopic createTopic() {
		return TopicBuilder.name("prodcut-create-events-topic")
		.partitions(3)
		.replicas(3)
		.configs(Map.of("main.insyc.replicas","2"))
		.build();
	}
	
}
