package com.inditex.prices_service.application;

import com.inditex.prices_service.domain.PriceRepository;
import com.inditex.prices_service.domain.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPrice(int productId, int brandId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new NoSuchElementException("No price found for the given parameters"));
    }
}
