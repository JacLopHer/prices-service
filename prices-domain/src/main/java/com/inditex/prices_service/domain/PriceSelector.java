package com.inditex.prices_service.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PriceSelector {
    private PriceSelector() {}
    public static Optional<Price> selectApplicable(List<Price> candidates) {
        return candidates.stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }
}

