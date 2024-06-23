package com.pharmacy_management.Data;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class Drug {
    private String drugCode;
    private String name;
    private String description;
    private double price;
    private int stock;
    private List<Supplier> suppliers;
    private TreeMap<Date, Purchase> purchaseHistory;

    public Drug(String name, String description, double price, int stock, List<Supplier> suppliers) {
        this.drugCode = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.suppliers = suppliers;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public TreeMap<Date, Purchase> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(TreeMap<Date, Purchase> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public void addPurchaseHistory(Date date, Purchase purchase) {
        purchaseHistory.put(date, purchase);
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    public void updateStock(int quantity) {
        stock += quantity;
    }

    public void updatePrice(double newPrice) {
        price = newPrice;
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
    }

}
