package com.inditex.prices_service.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
    Optional<PriceEntity> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
        int productId,
        int brandId,
        OffsetDateTime applicationDate1,
        OffsetDateTime applicationDate2
    );

    @Query(value = "SELECT * FROM prices WHERE product_id = :productId AND brand_id = :brandId AND :applicationDate BETWEEN start_date AND end_date ORDER BY priority DESC LIMIT 1", nativeQuery = true)
    Optional<PriceEntity> findBestCandidate(
        @Param("productId") int productId,
        @Param("brandId") int brandId,
        @Param("applicationDate") OffsetDateTime applicationDate
    );
}
