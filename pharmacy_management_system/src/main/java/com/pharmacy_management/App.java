package com.pharmacy_management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pharmacy_management.Data.Drug;
import com.pharmacy_management.Data.Supplier;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to Pharmacy Management System!");
        PharmacyManagementSystem pharmacyManagementSystem = new PharmacyManagementSystem();

        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("Please select an option by entering the number:");
                System.out.println("1. View all drugs and their suppliers");
                System.out.println("2. View purchase history for each drug");
                System.out.println("3. Add a new drug");
                System.out.println("4. Search for a drug");
                System.out.println("5. Exit");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Code to view all drugs and their suppliers
                        System.out.println("All drugs and their suppliers:");
                        for (Drug drug : pharmacyManagementSystem.viewAllDrugs()) {
                            System.out.println("-------------------------------------------------");
                            System.out.print("Drug Code: " + drug.getDrugCode() + ", ");
                            System.out.print("Drug: " + drug.getName() + ", Suppliers: ");
                            for (Supplier supplier : drug.getSuppliers()) {
                                System.out.print(supplier.getName() + " (" + supplier.getLocation() + "), ");
                            }
                            System.out.println(); // New line after each drug
                        }
                        break;
                    case 2:
                        // Code to view purchase history for each drug
                        // Implement viewing purchase history
                        break;
                    case 3:
                        try {
                            // Code to add a new drug
                            scanner.nextLine(); // Consume the leftover newline
                            System.out.println("Enter the drug name:");
                            String name = scanner.nextLine();

                            System.out.println("Enter the drug description:");
                            String description = scanner.nextLine();

                            System.out.println("Enter the drug price:");
                            double price = scanner.nextDouble();

                            System.out.println("Enter the drug stock:");
                            int stock = scanner.nextInt();

                            scanner.nextLine(); // Consume the leftover newline

                            System.out.println("Enter the supplier's name:");
                            String supplierName = scanner.nextLine();

                            System.out.println("Enter the supplier's location:");
                            String supplierLocation = scanner.nextLine();

                            List<Supplier> suppliers = new ArrayList<>();
                            suppliers.add(new Supplier(supplierName, supplierLocation));

                            Drug newDrug = new Drug(name, description, price, stock, suppliers);
                            pharmacyManagementSystem.addDrug(newDrug);
                            System.out.println("Drug added successfully!");

                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;
                    case 4:
                        // Code to search for a drug
                        try {
                            scanner.nextLine(); // Consume the leftover newline
                            System.out.println("Enter the drug code:");
                            String drugCode = scanner.nextLine();

                            Drug drug = pharmacyManagementSystem.searchDrug(drugCode);
                            if (drug != null) {
                                System.out.println("Drug found: ");
                                System.out.println("Drug Code: " + drug.getDrugCode());
                                System.out.println("Name: " + drug.getName());
                                System.out.println("Description: " + drug.getDescription());
                                System.out.println("Price: " + drug.getPrice());
                                System.out.println("Stock: " + drug.getStock());
                                System.out.println("Suppliers: ");
                                for (Supplier supplier : drug.getSuppliers()) {
                                    System.out.println(" - " + supplier.getName() + " (" + supplier.getLocation() + ")");
                                }
                            } else {
                                System.out.println("Drug with code " + drugCode + " not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 5);
        }
    }
}
