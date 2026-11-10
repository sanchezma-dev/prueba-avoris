package com.tech.infrastructure.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelSearchMessage {

    private UUID searchId;
    private String hotelId;
    private String checkIn;
    private String checkOut;
    private List<Integer> ages;
}
