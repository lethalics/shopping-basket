package com.r3pi.shoppingcart;

import com.r3pi.shoppingcart.model.PriceDetails;

import java.util.Map;

public interface PriceCalculator<T> {
    PriceDetails calculatePrice(Map<T, Integer> items);
}
