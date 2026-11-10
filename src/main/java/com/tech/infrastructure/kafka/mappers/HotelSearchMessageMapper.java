package com.tech.infrastructure.kafka.mappers;

import com.tech.application.domain.HotelSearch;
import com.tech.application.domain.SearchCriteria;
import com.tech.infrastructure.kafka.models.HotelSearchMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface HotelSearchMessageMapper {

    @Mapping(source = "criteria.hotelId", target = "hotelId")
    @Mapping(source = "criteria.checkIn", target = "checkIn", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "criteria.checkOut", target = "checkOut", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "criteria.ages", target = "ages")
    HotelSearchMessage toMessage(HotelSearch domain);

    default HotelSearch toDomain(HotelSearchMessage message) {
        SearchCriteria criteria = new SearchCriteria(
            message.getHotelId(),
            LocalDate.parse(message.getCheckIn()),
            LocalDate.parse(message.getCheckOut()),
            message.getAges()
        );
        return new HotelSearch(message.getSearchId(), criteria);
    }
}
