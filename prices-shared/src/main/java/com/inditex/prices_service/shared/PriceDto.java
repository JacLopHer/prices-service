package com.inditex.prices_service.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class PriceDto {
    private Long id;
    private int productId;
    private int brandId;
    private int priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private BigDecimal price;
    private String currency;

}
