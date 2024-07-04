package com.pharmacy_management;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy_management.Data.Drug;


public class ReportGenerator {
    private final PharmacyManagementSystem pharmacyManagementSystem;

    public ReportGenerator(PharmacyManagementSystem pharmacyManagementSystem) {
        this.pharmacyManagementSystem = pharmacyManagementSystem;
    }

    public List<DrugReport> generateDrugReport() {
        List<DrugReport> reports = new ArrayList<>();
        for (Drug drug : pharmacyManagementSystem.viewAllDrugs()) {
            DrugReport report = new DrugReport();
            report.setDrugCode(drug.getDrugCode());
            report.setDrugName(drug.getName());
            report.setPrice(drug.getPrice());
            report.setStock(drug.getStock());
            report.setSuppliers(drug.getSuppliers());
            reports.add(report);
        }
        return reports;
    }
}
