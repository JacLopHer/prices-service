package com.inditex.prices_service.domain;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(int productId, int brandId, LocalDateTime applicationDate);
}

