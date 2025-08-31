package com.inditex.prices_service.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :applicationDate BETWEEN p.startDate AND p.endDate")
    List<PriceEntity> findApplicablePrices(@Param("productId") int productId,
                                           @Param("brandId") int brandId,
                                           @Param("applicationDate") OffsetDateTime applicationDate);
}
