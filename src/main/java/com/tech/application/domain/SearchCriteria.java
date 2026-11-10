package com.tech.application.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record SearchCriteria(String hotelId, LocalDate checkIn, LocalDate checkOut, List<Integer> ages) {

    // FIXME DUDA SI DEJAR METODOS EN EL RECORD
    public SearchCriteria {
        ages = Collections.unmodifiableList(new ArrayList<>(ages));
    }

    public List<Integer> sortedAges() {
        return ages.stream().sorted().collect(java.util.stream.Collectors.toList());
    }

    public SearchCriteria withSortedAges() {
        return new SearchCriteria(hotelId, checkIn, checkOut, sortedAges());
    }
}
