package com.r3pi.shoppingcart.impl;


import com.r3pi.shoppingcart.PriceCalculator;
import com.r3pi.shoppingcart.model.Item;
import com.r3pi.shoppingcart.model.ItemFactory;
import com.r3pi.shoppingcart.model.PriceDetails;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ItemPriceCalculatorTest {

    @Test
    public void testCalculatePriceWithTax() {
        PriceCalculator<Item> priceCalculator = new ItemPriceCalculator(9);

        Map<Item, Integer> items = new HashMap<>();
        items.put(ItemFactory.create("Apple"), 2);
        items.put(ItemFactory.create("Orange"), 2);
        items.put(ItemFactory.create("Papaya"), 2);
        items.put(ItemFactory.create("Banana"), 1);

        PriceDetails result = priceCalculator.calculatePrice(items);

        assertEquals(new BigDecimal("3.25"), result.getSubTotal());
        assertEquals(new BigDecimal("1.0"), result.getTotalSavings());
        assertEquals(9, result.getTaxLevel().intValue());
        assertEquals(new BigDecimal("0.2925"), result.getTaxValue());
        assertEquals(new BigDecimal("3.5425"), result.getTotalWithTaxes());
    }


    @Test
    public void testCalculateWithoutTax() {
        PriceCalculator<Item> priceCalculator = new ItemPriceCalculator();

        Map<Item, Integer> items = new HashMap<>();
        items.put(ItemFactory.create("Apple"), 2);
        items.put(ItemFactory.create("Orange"), 2);
        items.put(ItemFactory.create("Papaya"), 2);
        items.put(ItemFactory.create("Banana"), 1);

        PriceDetails result = priceCalculator.calculatePrice(items);

        assertEquals(new BigDecimal("3.25"), result.getSubTotal());
        assertEquals(new BigDecimal("1.0"), result.getTotalSavings());
        assertNull( result.getTaxLevel());
        assertEquals(BigDecimal.ZERO, result.getTaxValue());
        assertEquals(new BigDecimal("3.25"), result.getTotalWithTaxes());
    }

    @Test
    public void testEmptyInput() {
        PriceCalculator<Item> priceCalculator = new ItemPriceCalculator();

        PriceDetails result = priceCalculator.calculatePrice(new HashMap<>());

        assertEquals(BigDecimal.ZERO, result.getSubTotal());
        assertEquals(BigDecimal.ZERO, result.getTotalSavings());
        assertEquals(BigDecimal.ZERO, result.getTaxValue());
        assertEquals(BigDecimal.ZERO, result.getTotalWithTaxes());
    }

}