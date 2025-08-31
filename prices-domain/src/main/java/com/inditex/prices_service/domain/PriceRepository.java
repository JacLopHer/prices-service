package com.inditex.prices_service.domain;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(int productId, int brandId, OffsetDateTime applicationDate);
    Optional<Price> findById(Integer id);
}
