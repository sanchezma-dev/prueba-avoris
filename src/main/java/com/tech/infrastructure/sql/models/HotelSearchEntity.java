package com.tech.infrastructure.sql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel_searches")
public class HotelSearchEntity {

    @Id
    @Column(name = "search_id", nullable = false)
    private UUID searchId;

    @Column(name = "hotel_id", nullable = false)
    private String hotelId;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name = "ages", nullable = false, columnDefinition = "integer[]")
    private List<Integer> ages;
}
