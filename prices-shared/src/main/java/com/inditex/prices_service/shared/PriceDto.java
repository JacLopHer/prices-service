package com.inditex.prices_service.shared;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceDto {
    private int productId;
    private int brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;
    private String curr;

    public PriceDto(int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.curr = curr;
    }

    // Getters
    public int getProductId() { return productId; }
    public int getBrandId() { return brandId; }
    public int getPriceList() { return priceList; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public BigDecimal getPrice() { return price; }
    public String getCurr() { return curr; }
}

