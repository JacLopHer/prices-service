package com.inditex.prices_service.infrastructure.db;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id", nullable = false)
    private int brandId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "price_list", nullable = false)
    private int priceList;

    @Column(name = "product_id", nullable = false)
    private int productId;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "curr", nullable = false)
    private String curr;

    public PriceEntity() {}

    public PriceEntity(Long id, int brandId, LocalDateTime startDate, LocalDateTime endDate, int priceList, int productId, int priority, BigDecimal price, String curr) {
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

