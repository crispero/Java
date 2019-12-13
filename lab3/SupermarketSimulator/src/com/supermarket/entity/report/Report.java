package com.supermarket.entity.report;

import com.supermarket.entity.basket.Basket;
import com.supermarket.entity.product.Product;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.logger.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private Map<Basket, BigDecimal> purchases = new HashMap<>();

    public Report() {}

    public void addPurchase(Basket basket, BigDecimal cost) {
        this.purchases.put(basket, cost);
    }

    public void getDailyReport() {
        Logger.printDailyReport();
        for (Map.Entry<Basket, BigDecimal> purchase : purchases.entrySet()) {
            for (SupermarketProduct product : purchase.getKey().getProducts()) {
                Logger.printPurchaseInfoReport(product, purchase.getValue());
            }
        }
    }
}
