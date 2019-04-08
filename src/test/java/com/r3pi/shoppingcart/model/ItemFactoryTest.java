package com.r3pi.shoppingcart.model;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ItemFactoryTest {

    @Test
    public void testCreate() {
        Item apple = new Item("Apple", new BigDecimal("0.25"));
        Item orange = new Item("Orange", new BigDecimal("0.30"));
        Item banana = new Item("Banana", new BigDecimal("0.15"));
        Item papaya = new Item("Papaya", new BigDecimal("0.5"), 3, new Discount("Papaya 3 for 1.00", new BigDecimal("0.5")));


        Item resultApple = ItemFactory.create("Apple");
        Item resultOrange = ItemFactory.create("Orange");
        Item resultBanana = ItemFactory.create("Banana");
        Item resultPapaya = ItemFactory.create("Papaya");

        assertEquals(apple, resultApple);
        assertEquals(orange, resultOrange);
        assertEquals(banana, resultBanana);
        assertEquals(papaya, resultPapaya);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInvalidInput() {
        ItemFactory.create("Pear");
    }

}