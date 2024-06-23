package com.pharmacy_management.DataAccessObject.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pharmacy_management.Data.Supplier;
import com.pharmacy_management.Database.DatabaseConnection;


public class SupplierDAO implements SupplierDAOInterface {

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM suppliers";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                Supplier supplier = new Supplier( name, location);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        } 

        return suppliers;
    }

    @Override
    public Supplier getSupplierById(String id) {
        Supplier supplier = null;
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
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                supplier = new Supplier( name, location);
                return supplier;
            }
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        } 

        return supplier;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO suppliers (name, location) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        } 
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "UPDATE suppliers SET name = ?, location = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getLocation());
            statement.setString(3, supplier.getSupplierID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        } 
    }

    @Override
    public void deleteSupplier(String id) {
        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM suppliers WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error" + " " + e.getMessage());
        }
    }
}
