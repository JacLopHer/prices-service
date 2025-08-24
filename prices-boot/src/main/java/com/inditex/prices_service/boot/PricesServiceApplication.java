package com.inditex.prices_service.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
    "com.inditex.prices_service.api",
    "com.inditex.prices_service.application",
    "com.inditex.prices_service.domain",
    "com.inditex.prices_service.infrastructure",
    "com.inditex.prices_service.shared"
})
@EnableJpaRepositories(basePackages = "com.inditex.prices_service.infrastructure.db")
@EntityScan(basePackages = "com.inditex.prices_service.infrastructure.db")
public class PricesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PricesServiceApplication.class, args);
    }
}
