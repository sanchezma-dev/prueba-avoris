package com.tech.application.ports.out;

import com.tech.application.domain.HotelSearch;

public interface HotelSearchPublisherPort {

    void publish(final HotelSearch hotelSearch);
}
