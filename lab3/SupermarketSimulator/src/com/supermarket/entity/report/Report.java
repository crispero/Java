package com.supermarket.entity.report;

import com.supermarket.entity.basket.Basket;
import com.supermarket.entity.product.SupermarketProduct;
import com.supermarket.logger.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private final Map<Basket, BigDecimal> purchases = new HashMap<>();

    public Report() {}

    public void addPurchase(Basket basket, BigDecimal cost) {
        if (cost.compareTo(new BigDecimal(0)) > 0) {
            this.purchases.put(basket, cost);
        }
    }

    public void getDailyReport() {
        if (purchases.isEmpty()) {
            Logger.printEmptyDailyReport();
        } else {
            Logger.printDailyReport();
            for (Map.Entry<Basket, BigDecimal> purchase : purchases.entrySet()) {
                for (SupermarketProduct product : purchase.getKey().getProducts()) {
                    Logger.printPurchaseInfoReport(product, purchase.getValue());
                }
            }
        }
    }
}
