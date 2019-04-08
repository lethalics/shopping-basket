package com.r3pi;

import com.r3pi.shoppingcart.impl.ItemPriceCalculator;
import com.r3pi.shoppingcart.impl.ShoppingBasket;
import com.r3pi.shoppingcart.model.ItemFactory;

public class App {

    public static void main(String args[]) {

        ItemPriceCalculator priceCalculator = new ItemPriceCalculator(9);
        ShoppingBasket shoppingBasket = new ShoppingBasket(priceCalculator);

        for(String item : args) {
            shoppingBasket.add(ItemFactory.create(item));
        }

        shoppingBasket.printReceipt(System.out);
    }
}
