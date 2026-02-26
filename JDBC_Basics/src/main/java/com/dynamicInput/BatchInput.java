package com.dynamicInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BatchInput {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Scanner sc = new Scanner(System.in);

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial";
            String user = "postgres";
            String password = "greatmaster";

            Connection con = DriverManager.getConnection(url, user, password);

            // Taking Dynamic Input
            PreparedStatement pst = con.prepareStatement("INSERT INTO student VALUES(?, ?, ?)");

            // First batch
            pst.setInt(1, 7);
            pst.setString(2, "ABC");
            pst.setInt(3, 20);
            pst.addBatch();

            // Second batch
            pst.setInt(1, 8);
            pst.setString(2, "DEF");
            pst.setInt(3, 20);
            pst.addBatch();

            // Third batch
            pst.setInt(1, 12);
            pst.setString(2, "GHI");
            pst.setInt(3, 20);
            pst.addBatch();

            pst.executeBatch();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
