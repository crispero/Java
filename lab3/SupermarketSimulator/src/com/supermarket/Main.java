package com.supermarket;

import com.supermarket.entity.bill.Bill;
import com.supermarket.entity.customer.CustType;
import com.supermarket.entity.customer.Customer;
import com.supermarket.entity.discount.Discount;
import com.supermarket.entity.paymentmethod.PaymentMethod;
import com.supermarket.entity.product.ProductType;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.entity.supermarket.Supermarket;
import com.supermarket.logger.Logger;
import com.supermarket.util.DateUtils;
import com.supermarket.util.RandomUtils;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    private final static int NUMBER_OF_ACTIONS = 3;

    public static void main(String[] args) {
        try {
            Supermarket supermarket = new Supermarket(initializeProducts());
            Date date = new Date();
            runSupermarket(supermarket, date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<SupermarketProduct> initializeProducts() {
        List<SupermarketProduct> products = new ArrayList<>();
        products.add(
                new SupermarketProduct(
                        "milk",
                        new BigDecimal(42),
                        false,
                        new Discount(0),
                        ProductType.QUANTITY,
                        RandomUtils.generateRandomNumber(12))
        );
        products.add(
                new SupermarketProduct(
                        "cigarettes",
                        new BigDecimal(115),
                        true,
                        new Discount(0),
                        ProductType.QUANTITY,
                        RandomUtils.generateRandomNumber(8))
        );
        products.add(
                new SupermarketProduct(
                        "chocolate",
                        new BigDecimal(60),
                        false,
                        new Discount(0),
                        ProductType.QUANTITY,
                        RandomUtils.generateRandomNumber(4))
        );
        products.add(
                new SupermarketProduct(
                        "nuts",
                        new BigDecimal(100),
                        false,
                        new Discount(0),
                        ProductType.WEIGHT,
                        RandomUtils.generateRandomNumber(1000))
        );
        return products;
    }

    private static void runSupermarket(Supermarket supermarket, Date date) {
        Integer customerCount = 1;

        Logger.printProductsHaveBeenFormed(date);
        for (SupermarketProduct product : supermarket.getProducts()) {
            Logger.printProductInfo(product);
        }

        startSupermarket(supermarket, date);
        Date finishSupermarketWorkTime = DateUtils.getSupermarketCloseTime(date);

        while (date.getTime() < finishSupermarketWorkTime.getTime()) {
            int randomActionIndex = RandomUtils.generateRandomNumber(NUMBER_OF_ACTIONS);

            System.out.println(randomActionIndex);
            switch (randomActionIndex) {
                case (0):
                    addCustomerInSupermarket(supermarket, customerCount, date);
                    ++customerCount;
                    break;
                case (1):
                    addProductInBasket(supermarket, date);
                    break;
                case (2):
                    addCustomerToCashDesk(supermarket, date);
                    break;
                case (3):
                    payAtCashDesk(supermarket, date);
                    break;
            }
        }

        closeSupermarket(supermarket, date);
        supermarket.getReportForToday();
    }

    private static void startSupermarket(Supermarket supermarket, Date date) {
        supermarket.open();
        Logger.printSupermarketOpen(date);
    }

    private static void closeSupermarket(Supermarket supermarket, Date date) {
        supermarket.close();
        Logger.printSupermarketClose(date);
    }

    private static void addCustomerInSupermarket(
            Supermarket supermarket,
            Integer customerCount,
            Date date
    ) {
        String name = "Customer " + customerCount;
        CustType customerType = CustType.values()[RandomUtils.generateRandomNumber(2)];
        BigDecimal money = new BigDecimal(RandomUtils.generateRandomNumber(1000));
        BigDecimal card = new BigDecimal(RandomUtils.generateRandomNumber(500));
        BigDecimal bonus = new BigDecimal(RandomUtils.generateRandomNumber(100));
        PaymentMethod paymentMethod = PaymentMethod.values()[RandomUtils.generateRandomNumber(2)];

        Customer customer = new Customer(
                name,
                customerType,
                money,
                card,
                bonus,
                paymentMethod
        );

        supermarket.addCustomer(customer);

        Logger.printCustomerArrivedInSupermarket(date, name);
        DateUtils.simulateTime(date);
    }

    private static void addProductInBasket(Supermarket supermarket, Date date) {
        int customersSize = supermarket.getCustomers().size();
        if (customersSize > 0) {
            int customerIndex = RandomUtils.generateRandomNumber(customersSize);
            int productIndex = RandomUtils.generateRandomNumber(supermarket.getProducts().size());

            SupermarketProduct product = supermarket.getProductByIndex(productIndex);
            int quantity = RandomUtils.generateRandomNumber(product.getQuantity());
            Customer customer = supermarket.getCustomerByIndex(customerIndex);

            if (customer.canPutProductInBasket(product)) {
                customer.putProductInBasket(product, quantity);
                Logger.printCustomerAddedProductToBasket(date, customer.getName(), product.getName(), quantity);
                supermarket.setProductQuantity(productIndex, product.getQuantity() - quantity);
            } else {
                Logger.printCustomerIsChild(date, customer.getName(), product.getName());
            }
        } else {
            Logger.printNoCustomerInSupermarket(date);
        }

        DateUtils.simulateTime(date);
    }

    private static void addCustomerToCashDesk(Supermarket supermarket, Date date) {
        int customersSize = supermarket.getCustomers().size();
        int customerIndex = RandomUtils.generateRandomNumber(customersSize);

        Customer customer = supermarket.getCustomerByIndex(customerIndex);
        supermarket.addCustomerInCashDesk(customer);

        Logger.printCustomerArrivedToCashDesk(date, customer.getName());
        DateUtils.simulateTime(date);
    }

    private static void payAtCashDesk(Supermarket supermarket, Date date) {
        Queue<Customer> customersAtCashDesk = supermarket.getCashDesk().getCustomers();

        if (customersAtCashDesk.size() != 0) {
            Bill bill = supermarket.getCashDesk().getBasketCost();
            Customer customerAtCashDesk = supermarket.getCashDesk().getCustomer();
            if (customerAtCashDesk.canPayBill(bill)) {
                customerAtCashDesk.payBill(bill);
                supermarket.getCashDesk().addPurchaseToReport(bill.getPrice());
            } else {
                Logger.printCustomerCouldNotPay(date, customerAtCashDesk.getName());
            }

        } else {
            Logger.printQueueAtCashDeskIsEmpty(date);
        }
    }
}
