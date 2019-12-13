package com.supermarket.entity.basket;

import com.supermarket.entity.product.SupermarketProduct;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<SupermarketProduct> products = new ArrayList<>();

    public Basket() {}

    public List<SupermarketProduct> getProducts() {
        return products;
    }

    public void addProduct(SupermarketProduct product) {
        products.add(product);
    }

}
