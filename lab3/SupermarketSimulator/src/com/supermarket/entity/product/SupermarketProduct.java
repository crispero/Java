package com.supermarket.entity.product;

import com.supermarket.entity.discount.Discount;

import java.math.BigDecimal;

public class SupermarketProduct extends Product {
    private int quantity;

    public SupermarketProduct(String name, BigDecimal price, boolean isAdultOnly, Discount discount, ProductType type, int quantity) {
        super(name, price, isAdultOnly, discount, type);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
