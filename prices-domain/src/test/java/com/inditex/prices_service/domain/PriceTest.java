package com.inditex.prices_service.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {
    @Test
    void shouldCreatePriceWithAllFields() {
        OffsetDateTime start = OffsetDateTime.of(2020, 6, 14, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime end = OffsetDateTime.of(2020, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC);
        Price price = new Price(1L, 1, start, end, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        assertEquals(1L, price.getId());
        assertEquals(1, price.getBrandId());
        assertEquals(start, price.getStartDate());
        assertEquals(end, price.getEndDate());
        assertEquals(2, price.getPriceList());
        assertEquals(35455, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(new BigDecimal("25.45"), price.getPrice());
        assertEquals("EUR", price.getCurr());
    }

    @Test
    void shouldSetAndGetFields() {
        Price price = new Price();
        price.setId(2L);
        price.setBrandId(2);
        price.setPriceList(3);
        price.setProductId(12345);
        price.setPriority(5);
        price.setPrice(new BigDecimal("99.99"));
        price.setCurr("USD");
        OffsetDateTime start = OffsetDateTime.now();
        OffsetDateTime end = OffsetDateTime.now().plusDays(1);
        price.setStartDate(start);
        price.setEndDate(end);
        assertEquals(2L, price.getId());
        assertEquals(2, price.getBrandId());
        assertEquals(3, price.getPriceList());
        assertEquals(12345, price.getProductId());
        assertEquals(5, price.getPriority());
        assertEquals(new BigDecimal("99.99"), price.getPrice());
        assertEquals("USD", price.getCurr());
        assertEquals(start, price.getStartDate());
        assertEquals(end, price.getEndDate());
    }

    @Test
    void shouldHandleNullValues() {
        Price price = new Price();
        price.setPrice(null);
        price.setCurr(null);
        assertNull(price.getPrice());
        assertNull(price.getCurr());
    }


    @Test
    void shouldReturnStringRepresentation() {
        Price price = new Price();
        assertNotNull(price.toString());
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentType() {
        Price price = new Price();
        assertNotEquals(null, price);
        assertNotEquals("string", price);
    }

    @Test
    void shouldNotBeEqualIfFieldsAreDifferent() {
        OffsetDateTime date = OffsetDateTime.now();
        Price price1 = new Price(1L, 1, date, date, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        Price price2 = new Price(2L, 1, date, date, 2, 35455, 1, new BigDecimal("25.45"), "EUR");
        assertNotEquals(price1, price2);
    }

}
