package com.supermarket.entity.product;

import com.supermarket.entity.discount.Discount;

import java.math.BigDecimal;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final boolean isAdultOnly;
    private final Discount discount;
    private final ProductType type;

    Product(String name, BigDecimal price, boolean isAdultOnly, Discount discount, ProductType type) {
        this.name = name;
        this.price = price;
        this.isAdultOnly = isAdultOnly;
        this.discount = discount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAdultOnly() {
        return isAdultOnly;
    }

    public Discount getDiscount() {
        return discount;
    }

    public ProductType getType() {
        return type;
    }
}