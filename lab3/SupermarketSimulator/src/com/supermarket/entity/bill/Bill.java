package com.supermarket.entity.bill;

import java.math.BigDecimal;

public class Bill {
    private BigDecimal price;

    public Bill(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
