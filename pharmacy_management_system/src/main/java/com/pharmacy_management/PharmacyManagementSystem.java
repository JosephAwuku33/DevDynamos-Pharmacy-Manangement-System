package com.pharmacy_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.pharmacy_management.Data.Drug;
import com.pharmacy_management.Data.Supplier;

public class PharmacyManagementSystem {
    private HashMap<String, Drug> drugs;
    private HashMap<String, Supplier> suppliers;
    private Queue<String> sales;

    public PharmacyManagementSystem() {
        drugs = new HashMap<>();
        suppliers = new HashMap<>();
        sales = new LinkedList<>();
    }

    public void addDrug(Drug drug) {
        drugs.put(drug.getDrugCode(), drug);
    }

    public Drug searchDrug(String drugCode) {
        return drugs.get(drugCode);
    }

    public List<Drug> viewAllDrugs() {
        return new ArrayList<>(drugs.values());
    }

    public void recordSale(String drugCode) {
        sales.add(drugCode);
    }
    
    // Additional methods for purchase history and suppliers
    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.getSupplierID(), supplier);
    }

    public Supplier searchSupplier(String supplierID) {
        return suppliers.get(supplierID);
    }

    public List<Supplier> viewAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }

    public void recordPurchase(String drugCode, String supplierID) {
        Drug drug = drugs.get(drugCode);
        Supplier supplier = suppliers.get(supplierID);
        drug.getSuppliers().add(supplier);
    }

    public Queue<String> getSales() {
        return sales;
    }

    public void setSales(Queue<String> sales) {
        this.sales = sales;
    }

    public HashMap<String, Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(HashMap<String, Drug> drugs) {
        this.drugs = drugs;
    }

    public HashMap<String, Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(HashMap<String, Supplier> suppliers) {
        this.suppliers = suppliers;
    }


}
