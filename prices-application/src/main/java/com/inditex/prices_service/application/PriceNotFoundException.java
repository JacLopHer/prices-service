package com.inditex.prices_service.application;

/**
 * Exception thrown when no price is found for the given parameters.
 */
public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String message) {
        super(message);
    }
}

