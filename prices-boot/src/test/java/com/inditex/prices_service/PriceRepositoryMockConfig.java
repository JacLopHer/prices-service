package com.inditex.prices_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.mockito.Mockito;
import com.inditex.prices_service.domain.PriceRepository;

@TestConfiguration
public class PriceRepositoryMockConfig {
    @Bean
    @Primary
    public PriceRepository priceRepository() {
        return Mockito.mock(PriceRepository.class);
    }
}
