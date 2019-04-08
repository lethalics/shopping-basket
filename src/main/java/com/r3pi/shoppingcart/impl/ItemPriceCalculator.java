package com.r3pi.shoppingcart.impl;

import com.r3pi.shoppingcart.PriceCalculator;
import com.r3pi.shoppingcart.model.Item;
import com.r3pi.shoppingcart.model.PriceDetails;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

public class ItemPriceCalculator implements PriceCalculator<Item> {
    private Integer taxLevel;

    public ItemPriceCalculator() {}

    public ItemPriceCalculator(Integer taxLevel) {
        this.taxLevel = taxLevel;
    }


    @Override
    public PriceDetails calculatePrice(Map<Item, Integer> items) {
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalSavings = BigDecimal.ZERO;
        BigDecimal taxValue = BigDecimal.ZERO;
        //compute price per items of the same kind
        for(Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            Integer count = entry.getValue();
            BigDecimal price = item.getPrice().multiply(BigDecimal.valueOf(count), MathContext.DECIMAL32);
            subTotal = subTotal.add(price, MathContext.DECIMAL32);
            if(item.getDiscount() != null) {
                //calculate savings for every discounted item
                totalSavings = totalSavings.add(item.getDiscount().getValue().multiply(BigDecimal.valueOf(count), MathContext.DECIMAL32), MathContext.DECIMAL32);
            }
        }

        //total with savings
        BigDecimal total = subTotal.subtract(totalSavings, MathContext.DECIMAL32);
        //compute tax value from discounted total value
        if(taxLevel != null) {
            taxValue = total.multiply(BigDecimal.valueOf(taxLevel), MathContext.DECIMAL32).divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);
        }

        return new PriceDetails(total, totalSavings, taxLevel, taxValue);
    }

}
