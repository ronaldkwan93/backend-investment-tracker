package org.example.backendip.DTOs;

import java.math.BigDecimal;

public class UpdatePropertyDTO {
    private String address;
    private String suburb;
    private String state;
    private BigDecimal purchasePrice;
    private BigDecimal weeklyRent;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getWeeklyRent() {
        return weeklyRent;
    }

    public void setWeeklyRent(BigDecimal weeklyRent) {
        this.weeklyRent = weeklyRent;
    }
}
