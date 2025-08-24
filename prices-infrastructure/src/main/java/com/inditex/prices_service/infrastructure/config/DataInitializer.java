package com.inditex.prices_service.infrastructure.config;

import com.inditex.prices_service.infrastructure.db.PriceEntity;
import com.inditex.prices_service.infrastructure.db.SpringDataPriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(SpringDataPriceRepository repository) {
        return args -> {
            repository.save(new PriceEntity(null, 1, LocalDateTime.of(2020,6,14,0,0,0), LocalDateTime.of(2020,12,31,23,59,59), 1, 35455, 0, new BigDecimal("35.50"), "EUR"));
            repository.save(new PriceEntity(null, 1, LocalDateTime.of(2020,6,14,15,0,0), LocalDateTime.of(2020,6,14,18,30,0), 2, 35455, 1, new BigDecimal("25.45"), "EUR"));
            repository.save(new PriceEntity(null, 1, LocalDateTime.of(2020,6,15,0,0,0), LocalDateTime.of(2020,6,15,11,0,0), 3, 35455, 1, new BigDecimal("30.50"), "EUR"));
            repository.save(new PriceEntity(null, 1, LocalDateTime.of(2020,6,15,16,0,0), LocalDateTime.of(2020,12,31,23,59,59), 4, 35455, 1, new BigDecimal("38.95"), "EUR"));
        };
    }
}
