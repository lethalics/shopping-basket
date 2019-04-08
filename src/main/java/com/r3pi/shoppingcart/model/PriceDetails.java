package com.r3pi.shoppingcart.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class PriceDetails {
    private BigDecimal subTotal;
    private BigDecimal totalSavings;
    private Integer taxLevel;
    private BigDecimal taxValue;

    public PriceDetails(){}

    public PriceDetails(BigDecimal subTotal, BigDecimal savingsTotal, Integer taxLevel, BigDecimal taxValue) {
        this.subTotal = subTotal;
        this.totalSavings = savingsTotal;
        this.taxLevel = taxLevel;
        this.taxValue = taxValue;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(BigDecimal totalSavings) {
        this.totalSavings = totalSavings;
    }

    public Integer getTaxLevel() {
        return taxLevel;
    }

    public void setTaxLevel(Integer taxLevel) {
        this.taxLevel = taxLevel;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    public BigDecimal getTotalWithTaxes() {
        return taxValue != null ? subTotal.add(taxValue, MathContext.DECIMAL32) : subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceDetails that = (PriceDetails) o;

        if (subTotal != null ? !subTotal.equals(that.subTotal) : that.subTotal != null) return false;
        if (totalSavings != null ? !totalSavings.equals(that.totalSavings) : that.totalSavings != null) return false;
        if (taxLevel != null ? !taxLevel.equals(that.taxLevel) : that.taxLevel != null) return false;
        return taxValue != null ? taxValue.equals(that.taxValue) : that.taxValue == null;
    }

    @Override
    public int hashCode() {
        int result = subTotal != null ? subTotal.hashCode() : 0;
        result = 31 * result + (totalSavings != null ? totalSavings.hashCode() : 0);
        result = 31 * result + (taxLevel != null ? taxLevel.hashCode() : 0);
        result = 31 * result + (taxValue != null ? taxValue.hashCode() : 0);
        return result;
    }
}
