package com.collegemarketplace.college_marketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/college_marketplace"; 
     private static final String USER = "root"; 
     private static final String PASSWORD = "Root@123"; 

     public static Connection getConnection() {
         Connection connection = null;
         try {
             connection = DriverManager.getConnection(URL, USER, PASSWORD);
             System.out.println("Database connection established!");
         } catch (SQLException e) {
             System.out.println("Error connecting to the database: " + e.getMessage());
         }
         return connection;
     }
}