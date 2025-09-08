package com.inditex.prices_service.application;

import com.inditex.prices_service.domain.PriceRepository;
import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.shared.PriceDto;
import com.inditex.prices_service.shared.PriceMapper;
import com.inditex.prices_service.domain.PriceSelector;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Service for retrieving price information based on product, brand, and application date.
 */
@Service
public class PriceService {
    private final PriceRepository priceRepository;

    /**
     * Constructs a PriceService with the given PriceRepository.
     * @param priceRepository the repository to access price data
     */
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Retrieves the applicable price for the given product, brand, and date.
     * @param productId the product identifier
     * @param brandId the brand identifier
     * @param applicationDate the date for which the price is requested
     * @return the price data transfer object
     * @throws PriceNotFoundException if no price is found for the given parameters
     */
    public PriceDto getPrice(int productId, int brandId, OffsetDateTime applicationDate) {
        List<Price> candidates = priceRepository.findCandidates(productId, brandId, applicationDate);
        Price price = PriceSelector.selectApplicable(candidates)
                .orElseThrow(() -> new PriceNotFoundException("No price found for the given parameters"));
        return PriceMapper.toDto(price);
    }

    /**
     * Retrieves the price for the given price ID.
     * @param id the price identifier
     * @return the price data transfer object
     * @throws PriceNotFoundException if no price is found for the given ID
     */
    public PriceDto getPriceById(Integer id) {
        Price price = priceRepository.findById(id)
            .orElseThrow(() -> new PriceNotFoundException("No price found for id: " + id));
        return PriceMapper.toDto(price);
    }
}
