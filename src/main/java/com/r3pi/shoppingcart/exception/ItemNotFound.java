package com.r3pi.shoppingcart.exception;

public class ItemNotFound extends Exception {
    public ItemNotFound() {
        super();
    }

    public ItemNotFound(String message) {
        super(message);
    }

    public ItemNotFound(String message, Throwable throwable) {
        super(message, throwable);
    }
}
