package com.inditex.prices_service.infrastructure.db;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.junit.jupiter.api.Assertions.*;

class PriceEntityTest {
    @Test
    void shouldCreateEntityWithAllFields() {
        OffsetDateTime start = OffsetDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        PriceEntity entity = new PriceEntity(1L, 1, start, end, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        assertEquals(1L, entity.getId());
        assertEquals(1, entity.getBrandId());
        assertEquals(start, entity.getStartDate());
        assertEquals(end, entity.getEndDate());
        assertEquals(2, entity.getPriceList());
        assertEquals(35455, entity.getProductId());
        assertEquals(1, entity.getPriority());
        assertEquals(new BigDecimal("25.45"), entity.getPrice());
        assertEquals("EUR", entity.getCurr());
    }

    @Test
    void shouldSetAndGetFields() {
        PriceEntity entity = new PriceEntity();
        entity.setId(2L);
        entity.setBrandId(2);
        entity.setPriceList(3);
        entity.setProductId(12345);
        entity.setPriority(5);
        entity.setPrice(new BigDecimal("99.99"));
        entity.setCurr("USD");
        assertEquals(2L, entity.getId());
        assertEquals(2, entity.getBrandId());
        assertEquals(3, entity.getPriceList());
        assertEquals(12345, entity.getProductId());
        assertEquals(5, entity.getPriority());
        assertEquals(new BigDecimal("99.99"), entity.getPrice());
        assertEquals("USD", entity.getCurr());
    }

    @Test
    void shouldHandleNullValues() {
        PriceEntity entity = new PriceEntity();
        entity.setPrice(null);
        entity.setCurr(null);
        assertNull(entity.getPrice());
        assertNull(entity.getCurr());
    }

    @Test
    void shouldReturnStringRepresentation() {
        PriceEntity entity = new PriceEntity();
        assertNotNull(entity.toString());
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentType() {
        PriceEntity entity = new PriceEntity();
        assertNotEquals(null, entity);
        assertNotEquals("string", entity);
    }

    @Test
    void shouldNotBeEqualIfFieldsAreDifferent() {
        OffsetDateTime date = OffsetDateTime.now();
        PriceEntity entity1 = new PriceEntity(1L, 1, date, date, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        PriceEntity entity2 = new PriceEntity(2L, 1, date, date, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        assertNotEquals(entity1, entity2);
    }


    @Test
    void shouldSetStartAndEndDate() {
        PriceEntity entity = new PriceEntity();
        OffsetDateTime start = OffsetDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(2021, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        entity.setStartDate(start);
        entity.setEndDate(end);
        assertEquals(start, entity.getStartDate());
        assertEquals(end, entity.getEndDate());
    }

    @Test
    void equalsShouldHandleNullAndOtherType() {
        OffsetDateTime date = OffsetDateTime.now();
        PriceEntity entity = new PriceEntity(1L, 1, date, date, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        assertNotEquals(null, entity);
        assertNotEquals("string", entity);
    }

}
