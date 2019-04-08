package com.r3pi.shoppingcart.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Item {
    private String name;
    private BigDecimal unitPrice;
    private Integer packSize;
    private Discount discount;

    public Item(String name, BigDecimal unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.packSize = 1;
    }

    public Item(String name, BigDecimal unitPrice, Discount discount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.packSize = 1;
    }

    public Item(String name, BigDecimal unitPrice, Integer packSize, Discount discount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.packSize = packSize;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getPackSize() {
        return packSize;
    }

    public void setPackSize(Integer packSize) {
        this.packSize = packSize;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(packSize), MathContext.DECIMAL32);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (unitPrice != null ? !unitPrice.equals(item.unitPrice) : item.unitPrice != null) return false;
        if (packSize != null ? !packSize.equals(item.packSize) : item.packSize != null) return false;
        return discount != null ? discount.equals(item.discount) : item.discount == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (packSize != null ? packSize.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }
}
