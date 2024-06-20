package com.pharmacy_management.Data;

public class Supplier {
    private String supplierID;
    private String name;
    private String location;
    
    public Supplier(String supplierID, String name, String location) {
        this.supplierID = supplierID;
        this.name = name;
        this.location = location;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
