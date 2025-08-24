package com.inditex.prices_service.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Price {
    private Long id;
    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private int productId;
    private int priority;
    private BigDecimal price;
    private String curr;

    public Price() {}

    public Price(Long id, int brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList, int productId, int priority, BigDecimal price, String curr) {
        this.id = id;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public int getPriceList() { return priceList; }
    public void setPriceList(int priceList) { this.priceList = priceList; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCurr() { return curr; }
    public void setCurr(String curr) { this.curr = curr; }
}

