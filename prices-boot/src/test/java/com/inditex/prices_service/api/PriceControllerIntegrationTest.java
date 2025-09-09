package com.inditex.prices_service.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.inditex.prices_service.domain.PriceRepository;
import com.inditex.prices_service.domain.Price;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {com.inditex.prices_service.boot.PricesServiceApplication.class, PriceRepositoryTestConfig.class})
@AutoConfigureMockMvc
@SuppressWarnings("unused")
class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        Mockito.reset(priceRepository);
        Mockito.when(priceRepository.findApplicable(35455, 1, OffsetDateTime.parse("2020-06-14T10:00:00+02:00")))
            .thenReturn(Optional.of(new Price(
                1L,
                1,
                OffsetDateTime.parse("2020-06-14T00:00:00+02:00"),
                OffsetDateTime.parse("2020-12-31T23:59:59+02:00"),
                1,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR"
            )));
        Mockito.when(priceRepository.findApplicable(35455, 1, OffsetDateTime.parse("2020-06-14T16:00:00+02:00")))
            .thenReturn(Optional.of(new Price(
                2L,
                1,
                OffsetDateTime.parse("2020-06-14T15:00:00+02:00"),
                OffsetDateTime.parse("2020-06-14T18:30:00+02:00"),
                2,
                35455,
                0,
                new BigDecimal("25.45"),
                "EUR"
            )));
        Mockito.when(priceRepository.findApplicable(35455, 1, OffsetDateTime.parse("2020-06-14T21:00:00+02:00")))
            .thenReturn(Optional.of(new Price(
                3L,
                1,
                OffsetDateTime.parse("2020-06-14T00:00:00+02:00"),
                OffsetDateTime.parse("2020-12-31T23:59:59+02:00"),
                1,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR"
            )));
        Mockito.when(priceRepository.findApplicable(35455, 1, OffsetDateTime.parse("2020-06-15T10:00:00+02:00")))
            .thenReturn(Optional.of(new Price(
                4L,
                1,
                OffsetDateTime.parse("2020-06-15T00:00:00+02:00"),
                OffsetDateTime.parse("2020-06-15T11:00:00+02:00"),
                3,
                35455,
                0,
                new BigDecimal("30.50"),
                "EUR"
            )));
        Mockito.when(priceRepository.findApplicable(35455, 1, OffsetDateTime.parse("2020-06-16T21:00:00+02:00")))
            .thenReturn(Optional.of(new Price(
                5L,
                1,
                OffsetDateTime.parse("2020-06-15T16:00:00+02:00"),
                OffsetDateTime.parse("2020-12-31T23:59:59+02:00"),
                4,
                35455,
                0,
                new BigDecimal("38.95"),
                "EUR"
            )));
        Mockito.when(priceRepository.findApplicable(99999, 1, OffsetDateTime.parse("2020-06-14T10:00:00+02:00")))
            .thenReturn(Optional.empty());
    }

    @Test
    void test1_requestAt2020_06_14_10_00() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-14T10:00:00+02:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test2_requestAt2020_06_14_16_00() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-14T16:00:00+02:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T15:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-14T18:30:00+02:00"))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    void test3_requestAt2020_06_14_21_00() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-14T21:00:00+02:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-14T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void test4_requestAt2020_06_15_10_00() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-15T10:00:00+02:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T00:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-06-15T11:00:00+02:00"))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    void test5_requestAt2020_06_16_21_00() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-16T21:00:00+02:00")
                .param("productId", "35455")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.startDate").value("2020-06-15T16:00:00+02:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31T23:59:59+02:00"))
                .andExpect(jsonPath("$.price").value(38.95));
    }

    /**
     * Should return 404 Not Found when no price is available for the given parameters.
     */
    @Test
    void shouldReturn404IfNoPriceFound() throws Exception {
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-14T10:00:00+02:00")
                .param("productId", "99999")
                .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No price found for the given parameters"));
    }

    @Test
    void shouldReturn404WhenPriceNotFound() throws Exception {
        Mockito.when(priceRepository.findApplicable(99999, 1, OffsetDateTime.parse("2020-06-14T10:00:00+02:00")))
            .thenReturn(Optional.empty());
        mockMvc.perform(get("/prices")
                .param("date", "2020-06-14T10:00:00+02:00")
                .param("productId", "99999")
                .param("brandId", "1"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").exists());
    }
}
