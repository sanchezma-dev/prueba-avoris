package com.tech.application.ports.in;

import com.tech.application.domain.SearchCount;

import java.util.UUID;

public interface GetCountHotelSearchUseCasePort {

    SearchCount execute(final UUID searchId);
}
