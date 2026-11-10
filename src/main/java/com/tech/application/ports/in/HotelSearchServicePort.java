package com.tech.application.ports.in;

import com.tech.application.domain.HotelSearch;

public interface HotelSearchServicePort {

    void register(final HotelSearch hotelSearch);
}
