package com.inditex.prices_service.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Price {
    private Long id;
    private int brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private int priceList;
    private int productId;
    private int priority;
    /**
     * The field 'price' represents the final sale price according to the business model and client requirements.
     */
    @SuppressWarnings("squid:S1700")
    private BigDecimal price;
    private String curr;
}
