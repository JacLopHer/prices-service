package com.inditex.prices_service.shared;

import com.inditex.prices_service.domain.Price;

public class PriceMapper {
    public static PriceDto toDto(Price price) {
        if (price == null) return null;
        return new PriceDto(
            price.getProductId(),
            price.getBrandId(),
            price.getPriceList(),
            price.getStartDate(),
            price.getEndDate(),
            price.getPrice(),
            price.getCurr()
        );
    }
}

