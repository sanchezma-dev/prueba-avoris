package com.tech.application.ports.in;

import com.tech.application.domain.SearchCriteria;

import java.util.UUID;

public interface PostHotelSearchUseCasePort {

    UUID execute(final SearchCriteria criteria);
}
