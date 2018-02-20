package com.shoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {
    
    private final int scale = 2;
    private double tax;

    public TaxCalculator(double tax) {
        this.tax = tax;
    }

    public double calculateTax(ShoppingCart shoppingCart) {
        double taxAmount = shoppingCart.computeBill() * (this.tax / 100);
        return new BigDecimal(taxAmount).setScale(scale, RoundingMode.CEILING).doubleValue();
    }

}
