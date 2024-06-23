package com.pharmacy_management.Data;

import java.util.UUID;

public class Customer {
    private String customerID;
    private String name;
    private String contactInfo;

    public Customer(String customerID, String name, String contactInfo) {
        this.customerID = UUID.randomUUID().toString();
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    
}
