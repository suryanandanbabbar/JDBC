package com.dynamicInput;

import java.sql.*;
import java.util.Scanner;

public class DynamicInput {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            Scanner sc = new Scanner(System.in);

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial";
            String user = "postgres";
            String password = "greatmaster";

            Connection con = DriverManager.getConnection(url, user, password);

            // Taking Dynamic Input
            // '?' -> delimiter
            PreparedStatement pst = con.prepareStatement("INSERT INTO student VALUES(?, ?, ?)");
            pst.setInt(1, sc.nextInt());
            sc.nextLine(); // Removing leftover spaces
            pst.setString(2, sc.nextLine());
            pst.setInt(3, sc.nextInt());

            pst.execute();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
