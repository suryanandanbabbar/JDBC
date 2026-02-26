package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public static void main(String[] args) throws SQLException {
        try {
            // Step-1: Load the driver
            Class.forName("org.postgresql.Driver");

            // Step-2: Establishing the connection
            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial";
            String user = "postgres";
            String password = "greatmaster";

            Connection con = DriverManager.getConnection(url, user, password);

            // Step-3: Creating a statement
            Statement stmt = con.createStatement();

            // Step-4: Executing the query
            String sql = "INSERT INTO student VALUES(4, 'Karan', 100)";
            stmt.execute(sql);

            // Step-5: Closing the connection
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

