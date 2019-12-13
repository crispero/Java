package com.supermarket.entity.customer;

import com.supermarket.entity.basket.Basket;
import com.supermarket.entity.bill.Bill;
import com.supermarket.entity.paymentmethod.PaymentMethod;
import com.supermarket.entity.product.SupermarketProduct;

import java.math.BigDecimal;

public class Customer {
    private final String name;
    private final CustomerType customerType;
    private final Basket basket;
    private BigDecimal money;
    private BigDecimal card;
    private BigDecimal bonus;
    private final PaymentMethod paymentMethod;

    public Customer(
            String name,
            CustomerType customerType,
            BigDecimal money,
            BigDecimal card,
            BigDecimal bonus,
            PaymentMethod paymentMethod
    ) {
          this.name = name;
          this.customerType = customerType;
          this.basket = new Basket();
          this.money = money;
          this.card = card;
          this.bonus = bonus;
          this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Basket getBasket() {
        return basket;
    }

    public boolean canPutProductInBasket(SupermarketProduct product) {
        return getCustomerType() != CustomerType.CHILD || !product.isAdultOnly();
    }

    public void putProductInBasket(SupermarketProduct product, int quantity) {
        this.getBasket().addProduct(new SupermarketProduct(
                product.getName(),
                product.getPrice(),
                product.isAdultOnly(),
                product.getDiscount(),
                product.getType(),
                quantity
        ));
    }

    private BigDecimal getMoney() {
        return money;
    }

    private BigDecimal getBonus() {
        return bonus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    private BigDecimal getCard() {
        return card;
    }

    private void setCard(BigDecimal card) {
        this.card = card;
    }

    private void setMoney(BigDecimal money) {
        this.money = money;
    }

    private void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public void payBill(Bill bill) {
        BigDecimal price = bill.getPrice();

        if (getPaymentMethod() == PaymentMethod.CARD) {
            setCard(getCard().subtract(price));
        } else if (getPaymentMethod() == PaymentMethod.CASH) {
            setMoney(getMoney().subtract(price));
        } else if (getPaymentMethod() == PaymentMethod.BONUS) {
            setBonus(getBonus().subtract(price));
        }
    }

    public boolean canPayBill(Bill bill) {
        BigDecimal price = bill.getPrice();

        if (getPaymentMethod() == PaymentMethod.CARD) {
            return checkToCompare(price, getCard());
        } else if (getPaymentMethod() == PaymentMethod.CASH) {
            return checkToCompare(price, getMoney());
        } else if (getPaymentMethod() == PaymentMethod.BONUS) {
            return checkToCompare(price, getBonus());
        }

        return true;
    }

    private boolean checkToCompare(BigDecimal price, BigDecimal value) {
        return price.compareTo(value) <= 0;
    }
}
