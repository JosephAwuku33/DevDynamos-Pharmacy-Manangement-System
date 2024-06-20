package com.pharmacy_management.DataAccessObject.Drug;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.pharmacy_management.Data.Drug;
import com.pharmacy_management.Data.Purchase;
import com.pharmacy_management.Data.Supplier;
import com.pharmacy_management.Database.DatabaseConnection;
public class DrugDAO implements DrugDAOInterface {
    
    @Override
        public void addDrug(Drug drug) {
            String sql = "INSERT INTO drugs (drug_code, name, description, price, stock) VALUES (?, ?, ?, ?, ?)";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, drug.getDrugCode());
                statement.setString(2, drug.getName());
                statement.setString(3, drug.getDescription());
                statement.setDouble(4, drug.getPrice());
                statement.setInt(5, drug.getStock());
                statement.executeUpdate();
                System.out.println("Drug added successfully!");
            } catch (SQLException e) {
                System.out.print("Error" + " " + e.getMessage());
            }
        }
    
        @Override
        public Drug getDrugByCode(String drugCode) {
            String sql = "SELECT * FROM drugs WHERE drug_code = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, drugCode);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    // Assuming Supplier and Purchase objects are handled elsewhere
                    List<Supplier> suppliers = new ArrayList<>(); // Placeholder
                    TreeMap<Date, Purchase> purchaseHistory = new TreeMap<>(); // Placeholder
                    return new Drug(drugCode, name, description, price, stock, suppliers, purchaseHistory);
                }
            } catch (SQLException e) {
                System.out.print("Error" + " " + e.getMessage());
            }
            return null;
        }
    
        @Override
        public List<Drug> getAllDrugs() {
            List<Drug> drugs = new ArrayList<>();
            String sql = "SELECT * FROM drugs";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String drugCode = resultSet.getString("drug_code");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    double price = resultSet.getDouble("price");
                    int stock = resultSet.getInt("stock");
                    // Assuming Supplier and Purchase objects are handled elsewhere
                    List<Supplier> suppliers = new ArrayList<>(); // Placeholder
                    TreeMap<Date, Purchase> purchaseHistory = new TreeMap<>(); // Placeholder
                    drugs.add(new Drug(drugCode, name, description, price, stock, suppliers, purchaseHistory));
                }
            } catch (SQLException e) {
                System.out.print("Error" + " " + e.getMessage());
            }
            return drugs;
        }
    
        @Override
        public void updateDrug(Drug drug) {
            String sql = "UPDATE drugs SET name = ?, description = ?, price = ?, stock = ? WHERE drug_code = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, drug.getName());
                statement.setString(2, drug.getDescription());
                statement.setDouble(3, drug.getPrice());
                statement.setInt(4, drug.getStock());
                statement.setString(5, drug.getDrugCode());
                statement.executeUpdate();
                System.out.println("Drug updated successfully!");
            } catch (SQLException e) {
                System.out.print("Error" + " " + e.getMessage());
            }
        }
    
        @Override
        public void deleteDrug(String drugCode) {
            String sql = "DELETE FROM drugs WHERE drug_code = ?";
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, drugCode);
                statement.executeUpdate();
                System.out.println("Drug deleted successfully!");
            } catch (SQLException e) {
                System.out.print("Error" + " " + e.getMessage());
            }
        }
    }
    
