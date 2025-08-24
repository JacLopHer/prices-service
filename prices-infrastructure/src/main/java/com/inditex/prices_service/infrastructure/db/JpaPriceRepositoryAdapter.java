package com.inditex.prices_service.infrastructure.db;

import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.domain.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Repository
public class JpaPriceRepositoryAdapter implements PriceRepository {
    private final SpringDataPriceRepository springDataPriceRepository;

    public JpaPriceRepositoryAdapter(SpringDataPriceRepository springDataPriceRepository) {
        this.springDataPriceRepository = springDataPriceRepository;
    }

    @Override
    public Optional<Price> findApplicablePrice(int productId, int brandId, LocalDateTime applicationDate) {
        return springDataPriceRepository.findApplicablePrices(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparingInt(PriceEntity::getPriority))
                .map(this::toDomain);
    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getId(),
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );
    }
}

