package com.tech.application.usecases;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCount;
import com.tech.application.ports.in.GetCountHotelSearchUseCasePort;
import com.tech.application.ports.out.HotelSearchRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class GetCountHotelSearchUseCase implements GetCountHotelSearchUseCasePort {

    private final HotelSearchRepositoryPort hotelSearchRepositoryPort;

    @Override
    public SearchCount execute(final UUID searchId) {
        // FIXME(8): definición de búsqueda similar y si el recuento incluye la propia búsqueda — provisional: mismo hotelId, checkIn, checkOut y mismas edades sin importar el orden, incluyendo la propia búsqueda
        return hotelSearchRepositoryPort.findById(searchId)
            .map(HotelSearch::criteria)
            .map(criteria -> new SearchCount(searchId, criteria, hotelSearchRepositoryPort.countSimilar(criteria)))
            .orElseGet(SearchCount::empty);
    }
}
