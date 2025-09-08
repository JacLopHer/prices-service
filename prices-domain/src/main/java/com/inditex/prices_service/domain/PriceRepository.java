package com.inditex.prices_service.domain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepository {
    List<Price> findCandidates(int productId, int brandId, OffsetDateTime applicationDate);
    Optional<Price> findById(Integer id);
}
