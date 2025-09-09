package com.inditex.prices_service.domain;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicable(int productId, int brandId, OffsetDateTime applicationDate);
}
