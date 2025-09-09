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
    void findApplicableShouldReturnPrice() {
        Mockito.when(springDataPriceRepository.findBestCandidate(anyInt(), anyInt(), any()))
                .thenReturn(Optional.of(entity));
        Optional<Price> result = adapter.findApplicable(35455, 1, OffsetDateTime.now());
        assertTrue(result.isPresent());
        assertEquals(entity.getId(), result.get().getId());
    }

    @Test
    void findApplicableShouldReturnEmpty() {
        Mockito.when(springDataPriceRepository.findBestCandidate(anyInt(), anyInt(), any()))
                .thenReturn(Optional.empty());
        Optional<Price> result = adapter.findApplicable(35455, 1, OffsetDateTime.now());
        assertTrue(result.isEmpty());
    }
}
