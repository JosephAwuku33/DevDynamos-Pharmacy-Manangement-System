package com.pharmacy_management.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456789";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database!");
            } catch (SQLException e) {
                System.out.println("Error connecting database connection." + e.getMessage());
            }
        }
        return connection;
    }

    // public static void closeConnection() {
    //     if (connection != null) {
    //         try {
    //             connection.close();
    //             connection = null;
    //             System.out.println("Database connection closed.");
    //         } catch (SQLException e) {
    //             System.out.println("Error closing the database connection." + e.getMessage());
    //         }
    //     }
    // }
}
