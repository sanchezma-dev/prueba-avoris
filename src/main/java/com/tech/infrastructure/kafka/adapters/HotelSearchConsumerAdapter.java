package com.tech.infrastructure.kafka.adapters;

import com.tech.application.ports.in.HotelSearchServicePort;
import com.tech.infrastructure.kafka.mappers.HotelSearchMessageMapper;
import com.tech.infrastructure.kafka.models.HotelSearchMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class HotelSearchConsumerAdapter {

    private final HotelSearchServicePort hotelSearchServicePort;
    private final HotelSearchMessageMapper messageMapper;

    @KafkaListener(topics = "${app.kafka.topics.hotel-availability-searches}")
    public void consume(final HotelSearchMessage message) {
        log.info("Received hotel search message with searchId: {}", message.getSearchId());
        hotelSearchServicePort.register(messageMapper.toDomain(message));
    }
}
