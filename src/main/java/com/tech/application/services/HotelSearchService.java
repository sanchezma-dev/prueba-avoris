package com.tech.application.services;

import com.tech.application.domain.HotelSearch;
import com.tech.application.ports.in.HotelSearchServicePort;
import com.tech.application.ports.out.HotelSearchRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class HotelSearchService implements HotelSearchServicePort {

    private final HotelSearchRepositoryPort hotelSearchRepositoryPort;

    @Override
    public void register(final HotelSearch hotelSearch) {
        // FIXME(9): orden de las edades persistidas — provisional: se guardan ordenadas ascendentemente y /count las devuelve ordenadas
        hotelSearchRepositoryPort.save(new HotelSearch(hotelSearch.searchId(), hotelSearch.criteria().withSortedAges()));
        log.info("Registered hotel search with searchId: {}", hotelSearch.searchId());
    }
}
