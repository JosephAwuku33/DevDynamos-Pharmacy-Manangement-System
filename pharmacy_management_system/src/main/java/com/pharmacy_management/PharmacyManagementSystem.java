package com.pharmacy_management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.pharmacy_management.Data.Drug;
import com.pharmacy_management.Data.Supplier;
import com.pharmacy_management.DataAccessObject.Drug.DrugDAO;
import com.pharmacy_management.DataAccessObject.Supplier.SupplierDAO;

public class PharmacyManagementSystem {
    private HashMap<String, Drug> drugs;
    private HashMap<String, Supplier> suppliers;
    private Queue<String> sales;
    private final DrugDAO drugDAO;
    private final SupplierDAO supplierDAO;

    public PharmacyManagementSystem() {
        drugs = new HashMap<>();
        suppliers = new HashMap<>();
        sales = new LinkedList<>();
        drugDAO = new DrugDAO();
        supplierDAO = new SupplierDAO();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDrug(Drug drug) {
        try {
            drugs.put(drug.getDrugCode(), drug);
            drugDAO.addDrug(drug); // Add to database
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Drug searchDrug(String drugCode) {
        Drug drug = drugs.get(drugCode);
        if (drug == null) {
            drug = drugDAO.getDrugByCode(drugCode); // Search in database if not found in local map
            if (drug != null) {
                drugs.put(drugCode, drug); // Cache it locally
            }
        }
        return drug;
    }

    public List<Drug> viewAllDrugs() {
        if (drugs.isEmpty()) {
            List<Drug> allDrugs = drugDAO.getAllDrugs(); // Fetch all from database
            for (Drug drug : allDrugs) {
                drugs.put(drug.getDrugCode(), drug); // Cache locally
            }
        }
        return new ArrayList<>(drugs.values());
    }

    public void recordSale(String drugCode) {
        sales.add(drugCode);
        // You might want to persist sales data as well using a DAO
    }

    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.getSupplierID(), supplier);
        supplierDAO.addSupplier(supplier); // Add to database
    }

    public Supplier searchSupplier(String supplierID) {
        Supplier supplier = suppliers.get(supplierID);
        if (supplier == null) {
            supplier = supplierDAO.getSupplierById(supplierID); // Search in database if not found in local map
            if (supplier != null) {
                suppliers.put(supplierID, supplier); // Cache it locally
            }
        }
        return supplier;
    }

    public List<Supplier> viewAllSuppliers() {
        if (suppliers.isEmpty()) {
            List<Supplier> allSuppliers = supplierDAO.getAllSuppliers(); // Fetch all from database
            for (Supplier supplier : allSuppliers) {
                suppliers.put(supplier.getSupplierID(), supplier); // Cache locally
            }
        }
        return new ArrayList<>(suppliers.values());
    }

    public void recordPurchase(String drugCode, String supplierID) {
        Drug drug = searchDrug(drugCode);
        Supplier supplier = searchSupplier(supplierID);
        if (drug != null && supplier != null) {
            drug.getSuppliers().add(supplier);
            drugDAO.updateDrug(drug); // Update drug in database with new supplier info
        }
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
