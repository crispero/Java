package com.supermarket.handler.customer;

import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.product.Product;

public class CustomerHandler {
    private Customer customer;

    public CustomerHandler(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addProductToBasket(Product product) {

    }
}
