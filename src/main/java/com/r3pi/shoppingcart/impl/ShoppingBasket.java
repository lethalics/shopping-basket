package com.r3pi.shoppingcart.impl;

import com.r3pi.shoppingcart.model.Discount;
import com.r3pi.shoppingcart.exception.ItemNotFound;
import com.r3pi.shoppingcart.model.PriceDetails;
import com.r3pi.shoppingcart.Basket;
import com.r3pi.shoppingcart.PriceCalculator;
import com.r3pi.shoppingcart.Receipt;
import com.r3pi.shoppingcart.model.Item;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingBasket implements Basket<Item>, Receipt {
    private Map<Item, Integer> currentItems = new HashMap<>();
    private PriceCalculator<Item> priceCalculator;

    public ShoppingBasket(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }


    @Override
    public void add(Item item) {
        if(currentItems.containsKey(item)) {
            currentItems.put(item, currentItems.get(item) + 1);
        } else {
            currentItems.put(item, 1);
        }
    }

    @Override
    public void remove(Item item) throws ItemNotFound {
        if(!currentItems.containsKey(item)) {
            throw new ItemNotFound("Item not found in the shopping basket");
        }

        Integer newCount = currentItems.get(item) - 1;
        if(newCount > 0) {
            currentItems.put(item, newCount);
        } else {
            currentItems.remove(item);
        }

    }

    @Override
    public Map<Item, Integer> getContent() {
        return currentItems;
    }

    public void printReceipt(PrintStream ps) {
        DecimalFormat df = new DecimalFormat("0.00");
        String line = "----------------------------";
        PriceDetails priceDetails = priceCalculator.calculatePrice(this.getContent());
        List<Discount> savings = new ArrayList<>();

        //print header
        ps.println(String.format("%-7s %-4s %-6s %-5s", "Item", "Qty", "UnitPrice", "Price"));

        //print items rows
        for(Map.Entry<Item, Integer> entry : currentItems.entrySet()) {
            Item item = entry.getKey();
            Integer count = entry.getValue();
            ps.println(String.format("%-6s  %-3s  %-8s  %5s", item.getName(), count * item.getPackSize() + "X", df.format(item.getUnitPrice()), df.format(item.getPrice().multiply(BigDecimal.valueOf(count), MathContext.DECIMAL32))));
            if(item.getDiscount() != null) {
                for(int i = 0; i < count; i++) {
                    savings.add(item.getDiscount());
                }
                ps.println(String.format("  %-17s  %7s", "Savings:", "-" + df.format(item.getDiscount().getValue().multiply(BigDecimal.valueOf(count), MathContext.DECIMAL32))));
            }
        }

        //print sub total
        ps.println();
        ps.println(String.format("%-21s  %5s", "Sub-total", df.format(priceDetails.getSubTotal())));
        ps.println(line);
        //print savings if any
        if(!savings.isEmpty()) {
            ps.println("Savings");
            for(Discount discount : savings) {
                ps.println(String.format(" %.20s %-1s %7s", discount.getName(), " ", "-" + df.format(discount.getValue())));
            }
            ps.println();
            ps.println(String.format("%-20s %7s", "Total savings", "-" + df.format(priceDetails.getTotalSavings())));
            ps.println(line);
        }

        //print taxes if any
        if(priceDetails.getTaxLevel() != null) {
            ps.println(String.format("%-6s  %-13s  %5s", "Tax", priceDetails.getTaxLevel() + "%", df.format(priceDetails.getTaxValue())));
            ps.println(line);
        }

        //print total
        ps.println(String.format("%-21s  %5s", "Total", df.format(priceDetails.getTotalWithTaxes())));
    }

}
