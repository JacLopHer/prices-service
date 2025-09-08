package com.inditex.prices_service.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PriceSelectorTest {
    @Test
    void selectApplicable_returnsEmptyOptional_whenListIsEmpty() {
        Optional<Price> result = PriceSelector.selectApplicable(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void selectApplicable_returnsPriceWithHighestPriority() {
        Price low = new Price();
        low.setPriority(1);
        Price high = new Price();
        high.setPriority(5);
        Price mid = new Price();
        mid.setPriority(3);
        List<Price> prices = List.of(low, high, mid);
        Optional<Price> result = PriceSelector.selectApplicable(prices);
        assertTrue(result.isPresent());
        assertEquals(5, result.get().getPriority());
    }
}

