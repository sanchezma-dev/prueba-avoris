package com.tech.application.usecases;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCriteria;
import com.tech.application.exceptions.ApiResponseException;
import com.tech.application.ports.in.PostHotelSearchUseCasePort;
import com.tech.application.ports.out.HotelSearchPublisherPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class PostHotelSearchUseCase implements PostHotelSearchUseCasePort {

    private final HotelSearchPublisherPort hotelSearchPublisherPort;

    @Override
    public UUID execute(final SearchCriteria criteria) {
        validate(criteria);
        UUID searchId = UUID.randomUUID();
        hotelSearchPublisherPort.publish(new HotelSearch(searchId, criteria));
        log.info("Published hotel search with searchId: {}", searchId);
        return searchId;
    }

    private void validate(final SearchCriteria criteria) {
        if (criteria.ages().stream().anyMatch(Objects::isNull)) {
            throw ApiResponseException.of("ages must not contain null values", HttpStatus.BAD_REQUEST);
        }
        if (!criteria.checkOut().isAfter(criteria.checkIn())) {
            throw ApiResponseException.of("checkOut must be after checkIn", HttpStatus.BAD_REQUEST);
        }
    }
}
