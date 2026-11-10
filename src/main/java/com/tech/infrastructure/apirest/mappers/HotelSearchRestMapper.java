package com.tech.infrastructure.apirest.mappers;

import com.tech.application.domain.SearchCount;
import com.tech.application.domain.SearchCriteria;
import com.tech.infrastructure.apirest.models.CountResponseDTO;
import com.tech.infrastructure.apirest.models.SearchRequestDTO;
import com.tech.infrastructure.apirest.models.SearchResponseDTO;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HotelSearchRestMapper {

    SearchCriteria toSearchCriteria(SearchRequestDTO dto);

    SearchRequestDTO toSearchRequestDTO(SearchCriteria criteria);

    CountResponseDTO toCountResponseDTO(SearchCount searchCount);

    default SearchResponseDTO toSearchResponseDTO(UUID searchId) {
        SearchResponseDTO dto = new SearchResponseDTO();
        dto.setSearchId(searchId);
        return dto;
    }
}
