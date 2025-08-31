package com.inditex.prices_service.infrastructure.db;

import com.inditex.prices_service.boot.PricesServiceApplication;
import com.inditex.prices_service.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PricesServiceApplication.class, properties = "spring.sql.init.mode=never")
class JpaPriceRepositoryAdapterIntegrationTest {

    @Autowired
    private SpringDataPriceRepository springDataPriceRepository;

    private JpaPriceRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new JpaPriceRepositoryAdapter(springDataPriceRepository);
        springDataPriceRepository.deleteAll();
        PriceEntity entity1 = new PriceEntity();
        entity1.setBrandId(1);
        entity1.setStartDate(OffsetDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC));
        entity1.setEndDate(OffsetDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC));
        entity1.setPriceList(1);
        entity1.setProductId(35455);
        entity1.setPriority(0);
        entity1.setPrice(new BigDecimal("35.50"));
        entity1.setCurr("EUR");
        springDataPriceRepository.save(entity1);
    }

    @Test
    void shouldFindPriceByProductIdAndBrandId() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.UTC));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }
}
