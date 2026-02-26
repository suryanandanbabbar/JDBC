package CRUD;

import java.sql.*;
import java.util.Scanner;

// Create a table "Employee"
// Columns: ID, Name, Department, Email
// Create a new Maven project
// To do the CRUD operations on Employee table dynamically by taking the input from the user
// Give the options for the user like 1 -> Insert, 2 -> Update, 3 -> Delete, 4 -> Fetch All Records

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:5432/JDBCTutorial";
            String user = "postgres";
            String password = "greatmaster";

            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            // 1 -> Insert, 2 -> Update, 3 -> Delete, 4 -> Fetch All Records
            System.out.println("""
                    1 -> Insert
                    2 -> Update
                    3 -> Delete
                    4 -> Fetch All Records
                    """);

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                // INSERT
                case 1: {
                    System.out.println("Enter ID:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Name:");
                    String name = sc.nextLine();

                    System.out.println("Enter Department:");
                    String department = sc.nextLine();

                    System.out.println("Enter Email:");
                    String email = sc.nextLine();

                    PreparedStatement pst = con.prepareStatement(
                            "INSERT INTO employee (id, name, department, email) VALUES (?, ?, ?, ?)"
                    );

                    pst.setInt(1, id);
                    pst.setString(2, name);
                    pst.setString(3, department);
                    pst.setString(4, email);

                    pst.execute();
                    break;
                }

                // UPDATE
                case 2: {
                    System.out.println("Enter ID:");
                    int id = sc.nextInt();

                    System.out.println("""
                            What do you want to update?
                            1. Name
                            2. Department
                            3. Email
                            """);

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        // Name
                        case 1: {
                            System.out.println("Enter new Name:");
                            String name = sc.nextLine();

                            PreparedStatement pst = con.prepareStatement(
                                    "UPDATE employee SET name = ? WHERE id = ?"
                            );

                            pst.setString(1, name);
                            pst.setInt(2, id);

                            pst.executeUpdate();
                            break;
                        }

                        // Department
                        case 2: {
                            System.out.println("Enter new Department:");
                            String department = sc.nextLine();

                            PreparedStatement pst = con.prepareStatement(
                                    "UPDATE employee SET department = ? WHERE id = ?"
                            );

                            pst.setString(1, department);
                            pst.setInt(2, id);

                            pst.executeUpdate();
                            break;
                        }

                        // Email
                        case 3: {
                            System.out.println("Enter new Email:");
                            String email = sc.nextLine();

                            PreparedStatement pst = con.prepareStatement(
                                    "UPDATE employee SET email = ? WHERE id = ?"
                            );

                            pst.setString(1, email);
                            pst.setInt(2, id);

                            pst.executeUpdate();
                            break;
                        }

                        default:
                            System.out.println("Invalid update choice.");
                    }

                    System.out.println("Update Done");
                    break;
                }

                // DELETE
                case 3: {
                    System.out.println("Enter ID:");
                    int id = sc.nextInt();

                    PreparedStatement pst = con.prepareStatement(
                            "DELETE FROM employee WHERE id = ?"
                    );

                    pst.setInt(1, id);
                    pst.executeUpdate();

                    System.out.println("Delete Done");
                    break;
                }

                // FETCH ALL
                case 4: {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

                    System.out.println("The records are:\n");

                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("id") + " " +
                                        rs.getString("name") + " " +
                                        rs.getString("department") + " " +
                                        rs.getString("email")
                        );
                    }

                    System.out.println("End");
                    break;
                }

                default:
                    System.out.println("Invalid option.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
