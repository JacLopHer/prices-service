package com.inditex.prices_service.infrastructure.db;

import com.inditex.prices_service.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class JpaPriceRepositoryAdapterTest {
    private SpringDataPriceRepository springDataPriceRepository;
    private JpaPriceRepositoryAdapter adapter;
    private PriceEntity entity;

    @BeforeEach
    void setUp() {
        springDataPriceRepository = Mockito.mock(SpringDataPriceRepository.class);
        adapter = new JpaPriceRepositoryAdapter(springDataPriceRepository);
        entity = new PriceEntity(1L, 1, OffsetDateTime.now(), OffsetDateTime.now().plusDays(1), 2, 35455, 1, new BigDecimal("25.45"), "EUR");
    }

    @Test
    void findApplicablePriceShouldReturnPrice() {
        Mockito.when(springDataPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(anyInt(), anyInt(), any(), any()))
                .thenReturn(Optional.of(entity));
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.now());
        assertTrue(result.isPresent());
        assertEquals(entity.getId(), result.get().getId());
    }

    @Test
    void findApplicablePriceShouldReturnEmpty() {
        Mockito.when(springDataPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(anyInt(), anyInt(), any(), any()))
                .thenReturn(Optional.empty());
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.now());
        assertTrue(result.isEmpty());
    }

    @Test
    void findByIdShouldReturnPrice() {
        Mockito.when(springDataPriceRepository.findById(1L)).thenReturn(Optional.of(entity));
        Optional<Price> result = adapter.findById(1);
        assertTrue(result.isPresent());
        assertEquals(entity.getId(), result.get().getId());
    }

    @Test
    void findByIdShouldReturnEmptyForNull() {
        Optional<Price> result = adapter.findById(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void findByIdShouldReturnEmptyForNonexistentId() {
        Mockito.when(springDataPriceRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Price> result = adapter.findById(999);
        assertTrue(result.isEmpty());
    }
}
