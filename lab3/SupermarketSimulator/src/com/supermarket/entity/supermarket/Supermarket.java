package com.supermarket.entity.supermarket;

import com.supermarket.entity.cashdesk.CashDesk;
import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.entity.report.Report;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private boolean isOpen;
    private List<SupermarketProduct> products;
    private List<Customer> customers = new ArrayList<>();
    private CashDesk cashDesk;
    private Report report;

    public Supermarket(List<SupermarketProduct> products) {
        this.products = products;
        this.report = new Report();
        this.cashDesk = new CashDesk(this.report);
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void setOpen(boolean open) {
        isOpen = open;
    }

    public void open() {
        if (!isOpen()) {
            setOpen(true);
        }
    }

    public void close() {
        if (isOpen()) {
            setOpen(false);
        }
    }

    public List<SupermarketProduct> getProducts() {
        return products;
    }

    public SupermarketProduct getProductByIndex(int index) {
        return products.get(index);
    }

    public void setProductQuantity(int productIndex, int quantity) {
        SupermarketProduct product = getProductByIndex(productIndex);
        product.setQuantity(quantity);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerByIndex(int index) {
        return customers.get(index);
    }

    public CashDesk getCashDesk() {
        return cashDesk;
    }

    public void addCustomerInCashDesk(Customer customer) {
        getCashDesk().addCustomer(customer);
    }

    public Report getReport() {
        return report;
    }

    public void getReportForToday() {
        this.getReport().getDailyReport();
    }
}
