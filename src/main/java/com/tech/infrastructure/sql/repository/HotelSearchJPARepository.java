package com.tech.infrastructure.sql.repository;

import com.tech.infrastructure.sql.models.HotelSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface HotelSearchJPARepository extends JpaRepository<HotelSearchEntity, UUID> {

    @Query(value = """
        SELECT COUNT(*)
        FROM hotel_searches
        WHERE hotel_id = :hotelId
          AND check_in = :checkIn
          AND check_out = :checkOut
          AND ages = :ages
        """, nativeQuery = true)
    long countSimilar(
            @Param("hotelId") String hotelId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("ages") List<Integer> ages
    );
}
