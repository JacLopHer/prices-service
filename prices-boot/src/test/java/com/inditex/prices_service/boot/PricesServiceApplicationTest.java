package com.inditex.prices_service.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class PricesServiceApplicationTest {
    @Test
    void contextLoads() {
        // Empty test to verify that the Spring context loads correctly
    }

    @Test
    void mainMethodRuns() {
        assertDoesNotThrow(() -> {
            PricesServiceApplication.main(new String[] {});
        }, "The main method should run without throwing any exception");
        // Verifies that the main method runs without errors
    }
}

