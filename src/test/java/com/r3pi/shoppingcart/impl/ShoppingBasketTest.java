package com.r3pi.shoppingcart.impl;

import com.r3pi.shoppingcart.Basket;
import com.r3pi.shoppingcart.PriceCalculator;
import com.r3pi.shoppingcart.model.Item;
import com.r3pi.shoppingcart.model.ItemFactory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {

    @Test
    public void testGroupItems() throws Exception {
        PriceCalculator<Item> priceCalculator = new ItemPriceCalculator(9);
        Basket<Item> shoppingBasket = new ShoppingBasket(priceCalculator);
        Item apple = ItemFactory.create("Apple");
        Item orange = ItemFactory.create("Orange");

        shoppingBasket.add(apple);
        shoppingBasket.add(orange);
        shoppingBasket.add(apple);
        shoppingBasket.add(orange);

        Map<Item, Integer> result  = shoppingBasket.getContent();

        assertEquals(2, result.get(apple).intValue());
        assertEquals(2, result.get(orange).intValue());

        shoppingBasket.remove(apple);

        assertEquals(1, result.get(apple).intValue());
    }

    @Test
    public void testPrintReceipt() {
        PriceCalculator<Item> priceCalculator = new ItemPriceCalculator(9);
        ShoppingBasket shoppingBasket = new ShoppingBasket(priceCalculator);

        Item apple = ItemFactory.create("Apple");
        Item orange = ItemFactory.create("Orange");
        Item banana = ItemFactory.create("Banana");
        Item papaya = ItemFactory.create("Papaya");

        shoppingBasket.add(apple);
        shoppingBasket.add(apple);
        shoppingBasket.add(orange);
        shoppingBasket.add(papaya);
        shoppingBasket.add(banana);
        shoppingBasket.add(papaya);



        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);

        shoppingBasket.printReceipt(ps);
        String result = out.toString();
        String[] lines = result.split(System.getProperty("line.separator"));


        assertEquals(18, lines.length);
        assertEquals("Item    Qty  UnitPrice Price", lines[0]);
        assertEquals("Papaya  6X   0.50       3.00", lines[1]);
        assertEquals("  Savings:             -1.00", lines[2]);
        assertEquals("Banana  1X   0.15       0.15", lines[3]);
        assertEquals("Orange  1X   0.30       0.30", lines[4]);
        assertEquals("Apple   2X   0.25       0.50", lines[5]);
        assertEquals("", lines[6]);
        assertEquals("Sub-total               2.95", lines[7]);
        assertEquals("----------------------------", lines[8]);
        assertEquals("Savings", lines[9]);
        assertEquals(" Papaya 3 for 1.00     -0.50", lines[10]);
        assertEquals(" Papaya 3 for 1.00     -0.50", lines[11]);
        assertEquals("", lines[12]);
        assertEquals("Total savings          -1.00", lines[13]);
        assertEquals("----------------------------", lines[14]);
        assertEquals("Tax     9%              0.27", lines[15]);
        assertEquals("----------------------------", lines[16]);
        assertEquals("Total                   3.22", lines[17]);

    }

}