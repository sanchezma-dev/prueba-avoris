package com.tech.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseException extends RuntimeException {

    private final HttpStatus status;

    private ApiResponseException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static ApiResponseException of(final String message, final HttpStatus status) {
        return new ApiResponseException(message, status);
    }
}
