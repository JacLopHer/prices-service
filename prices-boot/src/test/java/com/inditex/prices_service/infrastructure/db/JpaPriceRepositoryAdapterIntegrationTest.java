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
@SuppressWarnings("unused")
class JpaPriceRepositoryAdapterIntegrationTest {

    @Autowired
    private SpringDataPriceRepository springDataPriceRepository;

    private JpaPriceRepositoryAdapter adapter;

    private Long firstPriceId;

    @BeforeEach
    void setUp() {
        adapter = new JpaPriceRepositoryAdapter(springDataPriceRepository);
        springDataPriceRepository.deleteAll();
        // Insertar los 4 precios de ejemplo
        PriceEntity entity1 = new PriceEntity();
        entity1.setBrandId(1);
        entity1.setStartDate(OffsetDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.ofHours(2)));
        entity1.setEndDate(OffsetDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.ofHours(2)));
        entity1.setPriceList(1);
        entity1.setProductId(35455);
        entity1.setPriority(0);
        entity1.setPrice(new BigDecimal("35.50"));
        entity1.setCurr("EUR");
        PriceEntity savedEntity1 = springDataPriceRepository.save(entity1);
        firstPriceId = savedEntity1.getId();

        PriceEntity entity2 = new PriceEntity();
        entity2.setBrandId(1);
        entity2.setStartDate(OffsetDateTime.of(2020, 6, 14, 15, 0, 0, 0, ZoneOffset.ofHours(2)));
        entity2.setEndDate(OffsetDateTime.of(2020, 6, 14, 18, 30, 0, 0, ZoneOffset.ofHours(2)));
        entity2.setPriceList(2);
        entity2.setProductId(35455);
        entity2.setPriority(1);
        entity2.setPrice(new BigDecimal("25.45"));
        entity2.setCurr("EUR");
        springDataPriceRepository.save(entity2);

        PriceEntity entity3 = new PriceEntity();
        entity3.setBrandId(1);
        entity3.setStartDate(OffsetDateTime.of(2020, 6, 15, 0, 0, 0, 0, ZoneOffset.ofHours(2)));
        entity3.setEndDate(OffsetDateTime.of(2020, 6, 15, 11, 0, 0, 0, ZoneOffset.ofHours(2)));
        entity3.setPriceList(3);
        entity3.setProductId(35455);
        entity3.setPriority(1);
        entity3.setPrice(new BigDecimal("30.50"));
        entity3.setCurr("EUR");
        springDataPriceRepository.save(entity3);

        PriceEntity entity4 = new PriceEntity();
        entity4.setBrandId(1);
        entity4.setStartDate(OffsetDateTime.of(2020, 6, 15, 16, 0, 0, 0, ZoneOffset.ofHours(2)));
        entity4.setEndDate(OffsetDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.ofHours(2)));
        entity4.setPriceList(4);
        entity4.setProductId(35455);
        entity4.setPriority(1);
        entity4.setPrice(new BigDecimal("38.95"));
        entity4.setCurr("EUR");
        springDataPriceRepository.save(entity4);
    }

    @Test
    void shouldFindPriceByProductIdAndBrandId() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
        assertEquals(1, result.get().getPriceList());
    }

    @Test
    void shouldFindPriceAt2020_06_14_16_00() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 14, 16, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("25.45"), result.get().getPrice());
        assertEquals(2, result.get().getPriceList());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }

    @Test
    void shouldFindPriceAt2020_06_14_21_00() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 14, 21, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(1, result.get().getPriceList());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }

    @Test
    void shouldFindPriceAt2020_06_15_10_00() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 15, 10, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("30.50"), result.get().getPrice());
        assertEquals(3, result.get().getPriceList());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }

    @Test
    void shouldFindPriceAt2020_06_16_21_00() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2020, 6, 16, 21, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isPresent(), "El precio debería existir");
        assertEquals(new BigDecimal("38.95"), result.get().getPrice());
        assertEquals(4, result.get().getPriceList());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }

    @Test
    void shouldReturnEmptyWhenNoPriceForDate() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 1, OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertFalse(result.isPresent(), "No debería existir precio para esa fecha");
    }

    @Test
    void shouldReturnEmptyWhenProductDoesNotExist() {
        Optional<Price> result = adapter.findApplicablePrice(99999, 1, OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertTrue(result.isEmpty(), "No debería existir precio aplicable");
    }

    @Test
    void shouldReturnEmptyWhenBrandDoesNotExist() {
        Optional<Price> result = adapter.findApplicablePrice(35455, 99, OffsetDateTime.of(2020, 6, 14, 10, 0, 0, 0, ZoneOffset.ofHours(2)));
        assertFalse(result.isPresent(), "No debería existir precio para brand inexistente");
    }

    @Test
    void shouldReturnEmptyWhenFindByIdWithNull() {
        Optional<Price> result = adapter.findById(null);
        assertTrue(result.isEmpty(), "No debería existir precio para id nulo");
    }

    @Test
    void shouldReturnPriceWhenFindByIdWithExistingId() {
        Optional<Price> result = adapter.findById(firstPriceId.intValue());
        assertTrue(result.isPresent(), "Debe devolver el precio si el id existe");
        assertEquals(new BigDecimal("35.50"), result.get().getPrice());
        assertEquals(1, result.get().getBrandId());
        assertEquals(35455, result.get().getProductId());
        assertEquals("EUR", result.get().getCurr());
    }

    @Test
    void shouldReturnEmptyWhenFindByIdWithNonExistingId() {
        Optional<Price> result = adapter.findById(99999);
        assertTrue(result.isEmpty(), "No debería existir precio para id inexistente");
    }

    @Test
    void shouldReturnEmptyWhenFindByIdWithValidId() {
        Optional<Price> result = adapter.findById(firstPriceId.intValue());
        assertTrue(result.isPresent(), "El precio debería existir por id");
        assertEquals(firstPriceId, result.get().getId());
    }
}
