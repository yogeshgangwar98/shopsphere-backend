package com.shopsphere.product.dto;

import java.math.BigDecimal;

public class ProductResponse {
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;
    private Integer stockQuantity;
    private Boolean active;

    public ProductResponse(){}

    public ProductResponse(Long id, String name, BigDecimal price, String sku, Integer stockQuantity, Boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.stockQuantity = stockQuantity;
        this.active = active;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
