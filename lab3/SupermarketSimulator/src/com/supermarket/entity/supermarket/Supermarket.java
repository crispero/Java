package com.supermarket.entity.supermarket;

import com.supermarket.entity.cashdesk.CashDesk;
import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.entity.report.Report;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private boolean isOpen;
    private final List<SupermarketProduct> products;
    private final List<Customer> customers = new ArrayList<>();
    private final CashDesk cashDesk;
    private final Report report;

    public Supermarket(List<SupermarketProduct> products) {
        this.products = products;
        this.report = new Report();
        this.cashDesk = new CashDesk(this.report);
    }

    private boolean isOpen() {
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

    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void deleteAllCustomers() {
        if (customers.size() > 0) {
            customers.clear();
        }
    }

    public void returnProducts() throws Exception {
        for (Customer customer : customers) {
            for (SupermarketProduct productInBasket : customer.getBasket().getProducts()) {
                SupermarketProduct findProduct = findProduct(productInBasket);
                findProduct.setQuantity(findProduct.getQuantity() + productInBasket.getQuantity());
            }
        }
    }

    private SupermarketProduct findProduct(SupermarketProduct product) throws Exception {
        for (SupermarketProduct supermarketProduct : products) {
            if (supermarketProduct.getName().equals(product.getName())) {
                return supermarketProduct;
            }
        }

        throw new Exception("Product not found");
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

    private Report getReport() {
        return report;
    }

    public void getReportForToday() {
        this.getReport().getDailyReport();
    }
}
