package com.tech.infrastructure.sql.mappers;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCriteria;
import com.tech.infrastructure.sql.models.HotelSearchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelSearchDomainMapper {

    @Mapping(source = "criteria.hotelId", target = "hotelId")
    @Mapping(source = "criteria.checkIn", target = "checkIn")
    @Mapping(source = "criteria.checkOut", target = "checkOut")
    @Mapping(source = "criteria.ages", target = "ages")
    HotelSearchEntity toEntity(HotelSearch domain);

    default HotelSearch toDomain(HotelSearchEntity entity) {
        SearchCriteria criteria = new SearchCriteria(
            entity.getHotelId(),
            entity.getCheckIn(),
            entity.getCheckOut(),
            entity.getAges()
        );
        return new HotelSearch(entity.getSearchId(), criteria);
    }
}
