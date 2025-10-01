package org.example.backendip.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdatePropertyDTO {
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Suburb is required")
    private String suburb;
    @NotBlank(message = "State is required")
    private String state;
    @NotNull(message = "Purchase price is required")
    private BigDecimal purchasePrice;
    @NotNull(message = "Weekly Rent is required")
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
