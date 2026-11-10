package com.tech.infrastructure.sql.adapters;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCriteria;
import com.tech.application.ports.out.HotelSearchRepositoryPort;
import com.tech.infrastructure.sql.mappers.HotelSearchDomainMapper;
import com.tech.infrastructure.sql.repository.HotelSearchJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HotelSearchRepositoryAdapter implements HotelSearchRepositoryPort {

    private final HotelSearchJPARepository hotelSearchJPARepository;
    private final HotelSearchDomainMapper hotelSearchDomainMapper;

    @Override
    public HotelSearch save(final HotelSearch hotelSearch) {
        return hotelSearchDomainMapper.toDomain(
            hotelSearchJPARepository.save(hotelSearchDomainMapper.toEntity(hotelSearch))
        );
    }

    @Override
    public Optional<HotelSearch> findById(final UUID searchId) {
        return hotelSearchJPARepository.findById(searchId)
            .map(hotelSearchDomainMapper::toDomain);
    }

    @Override
    public long countSimilar(final SearchCriteria criteria) {
        return hotelSearchJPARepository.countSimilar(
            criteria.hotelId(),
            criteria.checkIn(),
            criteria.checkOut(),
            criteria.sortedAges()
        );
    }
}
