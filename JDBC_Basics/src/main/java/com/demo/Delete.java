package com.demo;

import java.sql.*;

public class Delete {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial?user=postgres&password=greatmaster";
            Connection con = DriverManager.getConnection(url);

            Statement stm = con.createStatement();
            String sql = "DELETE FROM student WHERE id = 12";
            stm.execute(sql);

            con.close();
            System.out.println("Data deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
