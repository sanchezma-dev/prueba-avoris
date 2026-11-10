package com.tech.application.domain;

import java.util.UUID;

public record SearchCount(UUID searchId, SearchCriteria search, long count) {

    public static SearchCount empty() {
        return new SearchCount(null, null, 0);
    }
}
