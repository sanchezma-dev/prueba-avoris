package com.tech.infrastructure.apirest.adapters;

import com.tech.application.domain.SearchCount;
import com.tech.application.ports.in.GetCountHotelSearchUseCasePort;
import com.tech.application.ports.in.PostHotelSearchUseCasePort;
import com.tech.infrastructure.apirest.adapters.api.HotelAvailabilityApi;
import com.tech.infrastructure.apirest.mappers.HotelSearchRestMapper;
import com.tech.infrastructure.apirest.models.CountResponseDTO;
import com.tech.infrastructure.apirest.models.SearchRequestDTO;
import com.tech.infrastructure.apirest.models.SearchResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class HotelControllerAdapter implements HotelAvailabilityApi {

    private final PostHotelSearchUseCasePort postHotelSearchUseCasePort;
    private final GetCountHotelSearchUseCasePort getCountHotelSearchUseCasePort;
    private final HotelSearchRestMapper mapper;

    @Override
    public ResponseEntity<SearchResponseDTO> search(final SearchRequestDTO searchRequestDTO) {
        UUID searchId = postHotelSearchUseCasePort.execute(mapper.toSearchCriteria(searchRequestDTO));
        return ResponseEntity.ok(mapper.toSearchResponseDTO(searchId));
    }

    @Override
    public ResponseEntity<CountResponseDTO> count(final UUID searchId) {
        SearchCount searchCount = getCountHotelSearchUseCasePort.execute(searchId);
        return ResponseEntity.ok(mapper.toCountResponseDTO(searchCount));
    }
}
