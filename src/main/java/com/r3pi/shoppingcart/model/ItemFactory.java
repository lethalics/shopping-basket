package com.r3pi.shoppingcart.model;

import java.math.BigDecimal;

public class ItemFactory {
    public static Item create(String item) {
        switch (item.toLowerCase()) {
            case "apple":
                return new Item("Apple", new BigDecimal("0.25"));
            case "orange":
                return new Item("Orange", new BigDecimal("0.30"));
            case "banana":
                return new Item("Banana", new BigDecimal("0.15"));
            case "papaya":
                return new Item("Papaya", new BigDecimal("0.5"), 3, new Discount("Papaya 3 for 1.00", new BigDecimal("0.5")));
            default:
                throw new IllegalArgumentException("Invalid item provided: " + item);

        }
    }
}
