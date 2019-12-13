package com.supermarket.entity.cashdesk;

import com.supermarket.entity.bill.Bill;
import com.supermarket.entity.customer.CustType;
import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.product.Product;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.entity.report.Report;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CashDesk {
    private Queue<Customer> customers = new ArrayDeque<>();
    private BigDecimal money = new BigDecimal(0);
    private Report report;

    public CashDesk(Report report) {
        this.report = report;
    }

    public Queue<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer() {
        return customers.element();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void addMoneyToCashDesk(BigDecimal money) {
        this.money = this.money.add(money);
    }

    public Bill getBasketCost() {
        Customer customer = getCustomer();
        List<SupermarketProduct> products = customer.getBasket().getProducts();
        BigDecimal totalPrice = new BigDecimal(0);

        for (SupermarketProduct product : products) {
            if (customer.getCustomerType() == CustType.RETIRED && product.getDiscount().getSize() != 0) {
                int productPriceWithDiscount = product.getPrice().intValue() * (100 - product.getDiscount().getSize()) / 100;
                totalPrice = totalPrice.add(new BigDecimal(productPriceWithDiscount * product.getQuantity()));
            }

            totalPrice = totalPrice.add(new BigDecimal(product.getPrice().intValue() * product.getQuantity()));
        }

        return new Bill(totalPrice);
    }

    public Report getReport() {
        return report;
    }

    public void addPurchaseToReport(BigDecimal cost) {
        getReport().addPurchase(getCustomer().getBasket(), cost);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
}
