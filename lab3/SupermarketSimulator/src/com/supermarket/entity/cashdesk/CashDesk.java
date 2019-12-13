package com.supermarket.entity.cashdesk;

import com.supermarket.entity.bill.Bill;
import com.supermarket.entity.customer.CustomerType;
import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.entity.report.Report;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CashDesk {
    private final Queue<Customer> customers = new ArrayDeque<>();
    private final Report report;

    public CashDesk(Report report) {
        this.report = report;
    }

    public Queue<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomer() {
        return customers.element();
    }

    public boolean canAddCustomer(Customer customer) {
        for (Customer customerAtCashDesk : customers) {
            if (customerAtCashDesk.equals(customer)) {
                return false;
            }
        }
        return true;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Bill getBasketCost() {
        Customer customer = getCustomer();
        List<SupermarketProduct> products = customer.getBasket().getProducts();
        BigDecimal totalPrice = new BigDecimal(0);

        for (SupermarketProduct product : products) {
            if (customer.getCustomerType() == CustomerType.RETIRED && product.getDiscount().getSize() != 0) {
                int productPriceWithDiscount = product.getPrice().intValue() * (100 - product.getDiscount().getSize()) / 100;
                totalPrice = totalPrice.add(new BigDecimal(productPriceWithDiscount * product.getQuantity()));
            } else {
                totalPrice = totalPrice.add(new BigDecimal(product.getPrice().intValue() * product.getQuantity()));
            }
        }

        return new Bill(totalPrice);
    }

    private Report getReport() {
        return report;
    }

    public void addPurchaseToReport(BigDecimal cost) {
        getReport().addPurchase(getCustomer().getBasket(), cost);
    }

    public void removeCustomer(Customer customer) {
        if (!customers.isEmpty()) {
            customers.remove(customer);
        }
    }
}
