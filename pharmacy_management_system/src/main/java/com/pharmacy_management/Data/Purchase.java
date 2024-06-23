package com.pharmacy_management.Data;

import java.util.Date;
import java.util.UUID;

public class Purchase {
    private String purchaseId;
    private Date date;
    private Customer buyer;
    private int quantity;
    private double totalAmount;

    public Purchase(Date date, Customer buyer, int quantity, double totalAmount) {
        this.purchaseId = UUID.randomUUID().toString();
        this.date = date;
        this.buyer = buyer;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getBuyer() {
        return buyer;
    }

    public void setBuyer(Customer buyer) {
        this.buyer = buyer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

}
