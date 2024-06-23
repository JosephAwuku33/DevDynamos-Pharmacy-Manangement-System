package com.pharmacy_management.DataAccessObject.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pharmacy_management.Data.Customer;
import com.pharmacy_management.Data.Purchase;
import com.pharmacy_management.DataAccessObject.Customer.CustomerDAO;
import com.pharmacy_management.Database.DatabaseConnection;

public class PurchaseDAO implements PurchaseDAOInterface {

    @Override
    public List<Purchase> getAllPurchases() {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchases";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                String customerId = resultSet.getString("customer_id");
                int quantity = resultSet.getInt("quantity");
                double totalAmount = resultSet.getDouble("total_amount");

                Customer buyer = new CustomerDAO().getCustomerById(customerId);
                purchases.add(new Purchase(date, buyer, quantity, totalAmount));
            }

        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }

        return purchases;
    }

    @Override
    public Purchase getPurchaseById(String id) {
        Purchase purchase = null;
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM suppliers WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Date date = resultSet.getDate("date");
                String customerId = resultSet.getString("customer_id");
                int quantity = resultSet.getInt("quantity");
                double totalAmount = resultSet.getDouble("total_amount");

                Customer buyer = new CustomerDAO().getCustomerById(customerId);
                purchase = new Purchase(date, buyer, quantity, totalAmount);
                return purchase;
            }
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
        return purchase;
    }

    @Override
    public List<Purchase> getPurchasesByDate(Date date) {
        List<Purchase> purchases = new ArrayList<>();

        String sql = "SELECT * FROM purchases WHERE date = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date purchaseDate = resultSet.getDate("date");
                String customerId = resultSet.getString("customer_id");
                int quantity = resultSet.getInt("quantity");
                double totalAmount = resultSet.getDouble("total_amount");

                Customer buyer = new CustomerDAO().getCustomerById(customerId);
                purchases.add(new Purchase(purchaseDate, buyer, quantity, totalAmount));
            }
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }

        return purchases;
    }

    @Override
    public List<Purchase> getPurchasesByCustomer(String customerId) {
        List<Purchase> purchases = new ArrayList<>();

        String sql = "SELECT * FROM purchases WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date date = resultSet.getDate("date");
                int quantity = resultSet.getInt("quantity");
                double totalAmount = resultSet.getDouble("total_amount");

                Customer buyer = new CustomerDAO().getCustomerById(customerId);
                purchases.add(new Purchase(date, buyer, quantity, totalAmount));
            }
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
        return purchases;
    }

    @Override
    public void addPurchase(Purchase purchase) {
        String sql = "INSERT INTO purchases (date, customer_id, quantity, total_amount) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(purchase.getDate().getTime()));
            statement.setString(2, purchase.getBuyer().getCustomerID());
            statement.setInt(3, purchase.getQuantity());
            statement.setDouble(4, purchase.getTotalAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        String sql = "UPDATE purchases SET date = ?, customer_id = ?, quantity = ?, total_amount = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(purchase.getDate().getTime()));
            statement.setString(2, purchase.getBuyer().getCustomerID());
            statement.setInt(3, purchase.getQuantity());
            statement.setDouble(4, purchase.getTotalAmount());
            statement.setString(5, purchase.getPurchaseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
    }

    @Override
    public void deletePurchase(String id) {
    
        String sql = "DELETE FROM purchases WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
    }
}
