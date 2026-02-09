package com.appsdeveloperblog.ws.products.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.ws.products.rest.CreateProductRestModel;

@Service
public class ProductsServiceImpl implements IProductService {

	KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public ProductsServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	@Override
	public String createProduct(CreateProductRestModel productRestModel) {

        String productId = UUID.randomUUID().toString();

        // TODO: persist Product Details into databse table before publishing an ProductCreatedEvent

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId, productRestModel.getTitle(),
                productRestModel.getPrice(),
                productRestModel.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
            kafkaTemplate.send("product-create-events-topic", productId, productCreatedEvent);

        future.whenComplete((result, exeption) -> {
            if (exeption != null) {
                LOGGER.error("Fail to send message: " + exeption.getMessage());
            } else {
                LOGGER.info("Message send succesfully: " + result.getRecordMetadata());
            }
        });

        LOGGER.info("Returning product id");


        return productId;
	}    
}
