package com.tech.application.domain;

import java.util.UUID;

public record HotelSearch(UUID searchId, SearchCriteria criteria) {
}
