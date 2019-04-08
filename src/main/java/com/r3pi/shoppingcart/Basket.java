package com.r3pi.shoppingcart;

import com.r3pi.shoppingcart.exception.ItemNotFound;

import java.util.Map;

public interface Basket<T> {
    void add(T item);
    void remove(T item) throws ItemNotFound;
    Map<T, Integer> getContent();
}
