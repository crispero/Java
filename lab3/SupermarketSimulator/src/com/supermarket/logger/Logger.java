package com.supermarket.logger;

import com.supermarket.entity.paymentmethod.PaymentMethod;
import com.supermarket.entity.product.SupermarketProduct;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void printSupermarketOpen(Date date) {
        System.out.println(getCurrentDateTemplate(date) + "Supermarket is opened");
    }

    public static void printSupermarketClose(Date date) {
        System.out.println(getCurrentDateTemplate(date) + "Supermarket is closed");
    }

    public static void printCustomerArrivedInSupermarket(Date date, String customerName) {
        System.out.println(getCurrentDateTemplate(date) + "New customer '" + customerName + "' arrived");
    }

    public static void printNoCustomerInSupermarket(Date date) {
        System.out.println(getCurrentDateTemplate(date) + "No customers in supermarket now");
    }

    public static void printCustomerArrivedToCashDesk(Date date, String customerName) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' arrived to cash desk");
    }

    public static void printCustomerAddedProductToBasket(Date date, String customerName, String productName, int quantity) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' added " + quantity + " '" + productName + "' to basket");
    }

    public static void printCustomerIsChild(Date date, String customerName, String productName) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' tried to added '" + productName + "' to basket, but the guard did not allow him");
    }

    public static void printProductsHaveBeenFormed(Date date) {
        System.out.println(getCurrentDateTemplate(date) + "Supermarket products have been formed: ");
    }

    public static void printProductInfo(SupermarketProduct product) {
        System.out.print("name: " + product.getName() + ", ");
        System.out.print("price: " + product.getPrice() + ", ");
        System.out.println("quantity: " + product.getQuantity());
    }

    public static void printQueueAtCashDeskIsEmpty(Date date) {
        System.out.println(getCurrentDateTemplate(date) + "Queue at the cash desk is empty");
    }

    public static void printCustomerCouldNotPay(Date date, String customerName) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' could not pay the bill");
    }

    public static void printDailyReport() {
        System.out.println("Daily report: ");
    }

    public static void printEmptyDailyReport() {
        System.out.println("Daily report is empty");
    }

    public static void printPurchaseInfoReport(SupermarketProduct product, BigDecimal cost) {
        System.out.println(product.getName() + " " + product.getQuantity() + " " + cost.toString());
    }

    public static void printCustomerAtCashDesk(Date date, String customerName, BigDecimal price) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' at the cash desk, amount to pay: " + price);
    }

    public static void printCustomerPaid(Date date, String customerName, BigDecimal price, PaymentMethod paymentMethod) {
        System.out.println(getCurrentDateTemplate(date) + "Customer '" + customerName + "' paid: " + price + " by " + paymentMethod);
    }

    private static String getCurrentDateTemplate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return "[" + dateFormat.format(date) + "] ";
    }
}
