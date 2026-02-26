package com.demo;

import java.sql.*;

public class Fetch {
    public static void main(String[] args)  {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial";
            String user = "postgres";
            String password = "greatmaster";

            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("The records are:\n");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("age"));
            }
            System.out.println("End");
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

