package com.inditex.prices_service.application;

import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.domain.PriceRepository;
import com.inditex.prices_service.shared.PriceDto;
import com.inditex.prices_service.shared.PriceMapper;
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
        when(priceRepository.findApplicablePrice(35455, 1, date)).thenReturn(Optional.of(price));

        PriceDto result = priceService.getPrice(35455, 1, date);
        PriceDto expected = PriceMapper.toDto(price);
        assertEquals(expected.getPriceList(), result.getPriceList());
        assertEquals(expected.getPrice(), result.getPrice());
        assertEquals(expected.getProductId(), result.getProductId());
        assertEquals(expected.getBrandId(), result.getBrandId());
        assertEquals(expected.getStartDate(), result.getStartDate());
        assertEquals(expected.getEndDate(), result.getEndDate());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    /**
     * Should throw PriceNotFoundException if no price is found for the given parameters.
     */
    @Test
    void shouldThrowExceptionIfNoPriceFound() {
        OffsetDateTime date = OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.UTC);
        when(priceRepository.findApplicablePrice(99999, 1, date)).thenReturn(Optional.empty());
        assertThrows(PriceNotFoundException.class, () -> priceService.getPrice(99999, 1, date));
    }
}
