package com.tech.application.ports.out;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCriteria;

import java.util.Optional;
import java.util.UUID;

public interface HotelSearchRepositoryPort {

    HotelSearch save(final HotelSearch hotelSearch);

    Optional<HotelSearch> findById(final UUID searchId);

    long countSimilar(final SearchCriteria criteria);
}
