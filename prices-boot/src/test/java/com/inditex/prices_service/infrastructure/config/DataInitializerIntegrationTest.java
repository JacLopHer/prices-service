package com.inditex.prices_service.infrastructure.config;

import com.inditex.prices_service.infrastructure.db.PriceEntity;
import com.inditex.prices_service.infrastructure.db.SpringDataPriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.inditex.prices_service.boot.PricesServiceApplication.class)
class DataInitializerIntegrationTest {
    @Autowired
    private SpringDataPriceRepository repository;

    @Test
    void shouldInitializeDataOnStartup() {
        List<PriceEntity> prices = repository.findAll();
        assertFalse(prices.isEmpty(), "La base de datos debe contener precios iniciales");
        assertEquals(4, prices.size(), "Debe haber 4 precios iniciales");
        assertTrue(prices.stream().anyMatch(p -> p.getPriceList() == 1 && p.getPrice().toString().equals("35.50")));
        assertTrue(prices.stream().anyMatch(p -> p.getPriceList() == 2 && p.getPrice().toString().equals("25.45")));
        assertTrue(prices.stream().anyMatch(p -> p.getPriceList() == 3 && p.getPrice().toString().equals("30.50")));
        assertTrue(prices.stream().anyMatch(p -> p.getPriceList() == 4 && p.getPrice().toString().equals("38.95")));
    }
}

