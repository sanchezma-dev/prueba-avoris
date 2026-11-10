package com.tech.infrastructure.kafka.adapters;

import com.tech.application.domain.HotelSearch;
import com.tech.application.exceptions.ApiResponseException;
import com.tech.application.ports.out.HotelSearchPublisherPort;
import com.tech.infrastructure.kafka.mappers.HotelSearchMessageMapper;
import com.tech.infrastructure.kafka.models.HotelSearchMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class HotelSearchPublisherAdapter implements HotelSearchPublisherPort {

    private static final long SEND_TIMEOUT_SECONDS = 5;

    private final KafkaTemplate<String, HotelSearchMessage> kafkaTemplate;
    private final HotelSearchMessageMapper messageMapper;
    private final String topic;

    public HotelSearchPublisherAdapter(
            final KafkaTemplate<String, HotelSearchMessage> kafkaTemplate,
            final HotelSearchMessageMapper messageMapper,
            @Value("${app.kafka.topics.hotel-availability-searches}") final String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageMapper = messageMapper;
        this.topic = topic;
    }

    @Override
    public void publish(final HotelSearch hotelSearch) {
        log.info("Publishing hotel search with searchId: {}", hotelSearch.searchId());
        try {
            kafkaTemplate.send(topic, hotelSearch.searchId().toString(), messageMapper.toMessage(hotelSearch))
                .get(SEND_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted while publishing hotel search {}", hotelSearch.searchId(), e);
            throw ApiResponseException.of("Unable to publish hotel search", HttpStatus.SERVICE_UNAVAILABLE);
        } catch (ExecutionException | TimeoutException e) {
            log.error("Failed to publish hotel search {}", hotelSearch.searchId(), e);
            throw ApiResponseException.of("Unable to publish hotel search", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
