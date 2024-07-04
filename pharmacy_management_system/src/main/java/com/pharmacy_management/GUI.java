package com.pharmacy_management;

import java.util.ArrayList;
import java.util.List;

import com.pharmacy_management.Data.Drug;
import com.pharmacy_management.Data.Supplier;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
    private final PharmacyManagementSystem pharmacyManagementSystem = new PharmacyManagementSystem();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pharmacy Management System");

        // Main layout
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));

        // Menu
        Label menuLabel = new Label("Please select an option:");
        Button viewDrugsButton = new Button("View all drugs and their suppliers");
        Button addDrugButton = new Button("Add a new drug");
        Button searchDrugButton = new Button("Search for a drug");
        Button generateReportButton = new Button("Generate Report");

        mainLayout.getChildren().addAll(menuLabel, viewDrugsButton, addDrugButton, searchDrugButton, generateReportButton);

        // View Drugs Scene
        VBox viewDrugsLayout = new VBox(10);
        viewDrugsLayout.setPadding(new Insets(10));
        final ListView<String> drugsListView = new ListView<>();
        Button backFromViewDrugsButton = new Button("Back to Menu");
        viewDrugsLayout.getChildren().addAll(drugsListView, backFromViewDrugsButton);
        Scene viewDrugsScene = new Scene(viewDrugsLayout, 400, 300);

        // Add Drug Scene
        GridPane addDrugLayout = new GridPane();
        addDrugLayout.setPadding(new Insets(10));
        addDrugLayout.setVgap(8);
        addDrugLayout.setHgap(10);
        TextField nameField = new TextField();
        TextField descriptionField = new TextField();
        TextField priceField = new TextField();
        TextField stockField = new TextField();
        TextField supplierNameField = new TextField();
        TextField supplierLocationField = new TextField();
        Button saveDrugButton = new Button("Save Drug");
        Button backFromAddDrugButton = new Button("Back to Menu");

        addDrugLayout.add(new Label("Name:"), 0, 0);
        addDrugLayout.add(nameField, 1, 0);
        addDrugLayout.add(new Label("Description:"), 0, 1);
        addDrugLayout.add(descriptionField, 1, 1);
        addDrugLayout.add(new Label("Price:"), 0, 2);
        addDrugLayout.add(priceField, 1, 2);
        addDrugLayout.add(new Label("Stock:"), 0, 3);
        addDrugLayout.add(stockField, 1, 3);
        addDrugLayout.add(new Label("Supplier Name:"), 0, 4);
        addDrugLayout.add(supplierNameField, 1, 4);
        addDrugLayout.add(new Label("Supplier Location:"), 0, 5);
        addDrugLayout.add(supplierLocationField, 1, 5);
        addDrugLayout.add(saveDrugButton, 0, 6);
        addDrugLayout.add(backFromAddDrugButton, 1, 6);
        Scene addDrugScene = new Scene(addDrugLayout, 400, 300);

        // Search Drug Scene
        GridPane searchDrugLayout = new GridPane();
        searchDrugLayout.setPadding(new Insets(10));
        searchDrugLayout.setVgap(8);
        searchDrugLayout.setHgap(10);
        TextField searchDrugField = new TextField();
        Button searchButton = new Button("Search");
        TextArea searchResultArea = new TextArea();
        Button backFromSearchDrugButton = new Button("Back to Menu");

        searchDrugLayout.add(new Label("Drug Code:"), 0, 0);
        searchDrugLayout.add(searchDrugField, 1, 0);
        searchDrugLayout.add(searchButton, 2, 0);
        searchDrugLayout.add(searchResultArea, 0, 1, 3, 1);
        searchDrugLayout.add(backFromSearchDrugButton, 0, 2);
        Scene searchDrugScene = new Scene(searchDrugLayout, 400, 300);

        // Report Scene
        VBox reportLayout = new VBox(10);
        reportLayout.setPadding(new Insets(10));
        TableView<Drug> reportTableView = new TableView<>();
        Button backFromReportButton = new Button("Back to Menu");
        reportLayout.getChildren().addAll(reportTableView, backFromReportButton);
        Scene reportScene = new Scene(reportLayout, 600, 400);

        // Configure the TableView for the report
        TableColumn<Drug, String> drugCodeColumn = new TableColumn<>("Drug Code");
        drugCodeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDrugCode()));

        TableColumn<Drug, String> drugNameColumn = new TableColumn<>("Drug Name");
        drugNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Drug, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPrice()));

        TableColumn<Drug, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStock()));

        TableColumn<Drug, String> suppliersColumn = new TableColumn<>("Suppliers");
        suppliersColumn.setCellValueFactory(cellData -> {
            List<Supplier> suppliers = cellData.getValue().getSuppliers();
            StringBuilder supplierNames = new StringBuilder();
            for (Supplier supplier : suppliers) {
                supplierNames.append(supplier.getName()).append(" (").append(supplier.getLocation()).append("), ");
            }
            return new javafx.beans.property.SimpleStringProperty(supplierNames.toString());
        });

        List<TableColumn<Drug, ?>> columns = new ArrayList<>();
        columns.add(drugCodeColumn);
        columns.add(drugNameColumn);
        columns.add(priceColumn);
        columns.add(stockColumn);
        columns.add(suppliersColumn);
        reportTableView.getColumns().addAll(columns);

        // Main Scene
        Scene mainScene = new Scene(mainLayout, 400, 300);

        // Button Actions
        viewDrugsButton.setOnAction(e -> {
            drugsListView.getItems().clear();
            List<Drug> drugs = pharmacyManagementSystem.viewAllDrugs();
            for (Drug drug : drugs) {
                String suppliers = drug.getSuppliers().stream()
                        .map(supplier -> supplier.getName() + " (" + supplier.getLocation() + ")")
                        .reduce((a, b) -> a + ", " + b).orElse("No suppliers");
                drugsListView.getItems().add("Drug Code: " + drug.getDrugCode() + ", Name: " + drug.getName() + ", Suppliers: " + suppliers);
            }
            extracted(primaryStage, viewDrugsScene);
        });

        backFromViewDrugsButton.setOnAction(e -> extracted(primaryStage, mainScene));
        addDrugButton.setOnAction(e -> extracted(primaryStage, addDrugScene));
        backFromAddDrugButton.setOnAction(e -> extracted(primaryStage, mainScene));
        searchDrugButton.setOnAction(e -> extracted(primaryStage, searchDrugScene));
        backFromSearchDrugButton.setOnAction(e -> extracted(primaryStage, mainScene));
        generateReportButton.setOnAction(e -> {
            ObservableList<Drug> drugReports = FXCollections.observableArrayList(pharmacyManagementSystem.viewAllDrugs());
            reportTableView.setItems(drugReports);
            extracted(primaryStage, reportScene);
        });
        backFromReportButton.setOnAction(e -> extracted(primaryStage, mainScene));

        saveDrugButton.setOnAction(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            String supplierName = supplierNameField.getText();
            String supplierLocation = supplierLocationField.getText();

            List<Supplier> suppliers = new ArrayList<>();
            suppliers.add(new Supplier(supplierName, supplierLocation));

            Drug newDrug = new Drug(name, description, price, stock, suppliers);
            pharmacyManagementSystem.addDrug(newDrug);
            System.out.println("Drug added successfully!");
            // Clear the fields
            nameField.clear();
            descriptionField.clear();
            priceField.clear();
            stockField.clear();
            supplierNameField.clear();
            supplierLocationField.clear();
            extracted(primaryStage, mainScene);
        });

        searchButton.setOnAction(e -> {
            String drugCode = searchDrugField.getText();
            Drug drug = pharmacyManagementSystem.searchDrug(drugCode);
            if (drug != null) {
                StringBuilder result = new StringBuilder();
                result.append("Drug Code: ").append(drug.getDrugCode()).append("\n");
                result.append("Name: ").append(drug.getName()).append("\n");
                result.append("Description: ").append(drug.getDescription()).append("\n");
                result.append("Price: ").append(drug.getPrice()).append("\n");
                result.append("Stock: ").append(drug.getStock()).append("\n");
                result.append("Suppliers: \n");
                for (Supplier supplier : drug.getSuppliers()) {
                    result.append(" - ").append(supplier.getName()).append(" (").append(supplier.getLocation()).append(")\n");
                }
                searchResultArea.setText(result.toString());
            } else {
                searchResultArea.setText("Drug with code " + drugCode + " not found.");
            }
        });

        // Show main scene
        extracted(primaryStage, mainScene);
        primaryStage.show();
    }

    private void extracted(Stage primaryStage, Scene mainScene) {
        primaryStage.setScene(mainScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
