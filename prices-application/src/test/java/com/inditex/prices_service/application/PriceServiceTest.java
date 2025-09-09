package com.inditex.prices_service.application;

import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.domain.PriceRepository;
import com.inditex.prices_service.shared.PriceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    /**
     * Should return the applicable price for valid parameters.
     */
    @Test
    void shouldReturnApplicablePrice() {
        OffsetDateTime date = OffsetDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.UTC);
        Price price = new Price(1L, 1, date.minusHours(1), date.plusHours(2), 2, 35455, 1, new java.math.BigDecimal("25.45"), "EUR");
        when(priceRepository.findApplicable(35455, 1, date)).thenReturn(Optional.of(price));

        PriceDto result = priceService.getPrice(35455, 1, date);
        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1, result.getBrandId());
        assertEquals("EUR", result.getCurrency());
        assertEquals(new java.math.BigDecimal("25.45"), result.getPrice());
    }

    @Test
    void shouldThrowExceptionWhenNoApplicablePrice() {
        OffsetDateTime date = OffsetDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.UTC);
        when(priceRepository.findApplicable(35455, 1, date)).thenReturn(Optional.empty());
        assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(35455, 1, date));
    }
}
