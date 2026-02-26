package CRUD;

import java.sql.*;
import java.util.Scanner;

public class Main2 {

    private static final String URL = "jdbc:postgresql://localhost:5432/JDBCTutorial";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "greatmaster";

    public static void main(String[] args) {

        try (
                Scanner sc = new Scanner(System.in);
                Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)
        ) {

            Class.forName("org.postgresql.Driver");

            while (true) {
                System.out.println("""
                        
                        1 -> Admin Menu
                        2 -> Student Menu
                        3 -> Exit
                        """);

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> adminMenu(sc, con);
                    case 2 -> studentMenu(sc, con);
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= ADMIN MENU ================= */

    private static void adminMenu(Scanner sc, Connection con) throws SQLException {

        while (true) {
            System.out.println("""
                    
                    1 -> Student Table
                    2 -> Course Table
                    3 -> Back
                    """);

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> studentCRUD(sc, con);
                case 2 -> courseCRUD(sc, con);
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    /* ================= STUDENT CRUD ================= */

    private static void studentCRUD(Scanner sc, Connection con) throws SQLException {

        System.out.println("""
                
                1 -> Insert
                2 -> Update
                3 -> Delete
                4 -> Retrieve All
                """);

        int option = sc.nextInt();

        switch (option) {

            case 1 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();

                String sql = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setInt(3, age);
                    pst.executeUpdate();
                    System.out.println("Student inserted successfully.");
                }
            }

            case 2 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                System.out.println("""
                        1 -> Update Name
                        2 -> Update Age
                        """);

                int ch = sc.nextInt();
                sc.nextLine();

                if (ch == 1) {
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();

                    String sql = "UPDATE student SET name=? WHERE id=?";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, name);
                        pst.setInt(2, id);
                        int rows = pst.executeUpdate();
                        System.out.println(rows > 0 ? "Updated successfully." : "No record found.");
                    }
                } else if (ch == 2) {
                    System.out.print("Enter new Age: ");
                    int age = sc.nextInt();

                    String sql = "UPDATE student SET age=? WHERE id=?";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setInt(1, age);
                        pst.setInt(2, id);
                        int rows = pst.executeUpdate();
                        System.out.println(rows > 0 ? "Updated successfully." : "No record found.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            case 3 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                String sql = "DELETE FROM student WHERE id=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    int rows = pst.executeUpdate();
                    System.out.println(rows > 0 ? "Deleted successfully." : "No record found.");
                }
            }

            case 4 -> {
                String sql = "SELECT * FROM student";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " | " +
                                        rs.getString("name") + " | " +
                                        rs.getInt("age")
                        );
                    }
                }
            }

            default -> System.out.println("Invalid option.");
        }
    }

    /* ================= COURSE CRUD ================= */

    private static void courseCRUD(Scanner sc, Connection con) throws SQLException {

        System.out.println("""
                
                1 -> Insert
                2 -> Update
                3 -> Delete
                4 -> Retrieve All
                """);

        int option = sc.nextInt();

        switch (option) {

            case 1 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter No. of Days: ");
                int days = sc.nextInt();

                String sql = "INSERT INTO course (id, name, noOfDays) VALUES (?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setInt(3, days);
                    pst.executeUpdate();
                    System.out.println("Course inserted successfully.");
                }
            }

            case 2 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                System.out.println("""
                        1 -> Update Name
                        2 -> Update No. of Days
                        """);

                int ch = sc.nextInt();
                sc.nextLine();

                if (ch == 1) {
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();

                    String sql = "UPDATE course SET name=? WHERE id=?";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setString(1, name);
                        pst.setInt(2, id);
                        pst.executeUpdate();
                        System.out.println("Updated successfully.");
                    }
                } else if (ch == 2) {
                    System.out.print("Enter new No. of Days: ");
                    int days = sc.nextInt();

                    String sql = "UPDATE course SET noOfDays=? WHERE id=?";
                    try (PreparedStatement pst = con.prepareStatement(sql)) {
                        pst.setInt(1, days);
                        pst.setInt(2, id);
                        pst.executeUpdate();
                        System.out.println("Updated successfully.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            case 3 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();

                String sql = "DELETE FROM course WHERE id=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                    System.out.println("Deleted successfully.");
                }
            }

            case 4 -> {
                String sql = "SELECT * FROM course";
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " | " +
                                        rs.getString("name") + " | " +
                                        rs.getInt("noOfDays")
                        );
                    }
                }
            }

            default -> System.out.println("Invalid option.");
        }
    }

    /* ================= STUDENT MENU ================= */

    private static void studentMenu(Scanner sc, Connection con) throws SQLException {

        System.out.print("Enter your Student ID: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM student WHERE id=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Your Details:");
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age")
                );
            } else {
                System.out.println("Student not found.");
            }
        }
    }
}