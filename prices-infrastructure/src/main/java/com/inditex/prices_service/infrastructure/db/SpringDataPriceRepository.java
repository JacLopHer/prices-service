package com.inditex.prices_service.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        int productId,
        int brandId,
        OffsetDateTime applicationDate1,
        OffsetDateTime applicationDate2
    );
}
