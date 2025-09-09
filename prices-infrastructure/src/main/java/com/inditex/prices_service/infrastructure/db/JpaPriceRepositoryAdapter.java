package com.inditex.prices_service.infrastructure.db;

import com.inditex.prices_service.domain.Price;
import com.inditex.prices_service.domain.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public class JpaPriceRepositoryAdapter implements PriceRepository {
    private final SpringDataPriceRepository springDataPriceRepository;

    public JpaPriceRepositoryAdapter(SpringDataPriceRepository springDataPriceRepository) {
        this.springDataPriceRepository = springDataPriceRepository;
    }

    @Override
    public Optional<Price> findApplicable(int productId, int brandId, OffsetDateTime applicationDate) {
        return springDataPriceRepository
            .findBestCandidate(productId, brandId, applicationDate)
            .map(this::toDomain);
    }


    @Override
    public Optional<Price> findById(Integer id) {
        if (id == null) return Optional.empty();
        return springDataPriceRepository.findById(id.longValue())
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
