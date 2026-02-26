package com.demo;

import java.sql.*;

public class Update {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial?user=postgres&password=greatmaster";
            Connection con = DriverManager.getConnection(url);

            Statement stm = con.createStatement();

            String sql = "UPDATE student SET name = 'Sarthak' WHERE id = 7";
            stm.execute(sql);

            con.close();

            System.out.println("Data Updated Successfully");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
