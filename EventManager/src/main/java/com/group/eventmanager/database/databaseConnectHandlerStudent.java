package com.group.eventmanager.database;

import com.group.eventmanager.model.Student;

import java.sql.*;
import java.util.ArrayList;

// Handles the database connection for the Student entity as well as CRUD operations
public class databaseConnectHandlerStudent {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    public String username = null;
    private String password = null;

    // Constructor to initialize the database connection
    public databaseConnectHandlerStudent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Deletes a student from the database based on the studentID
    public boolean deleteStudent(int studentID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Student WHERE studentID = ?");
            ps.setInt(1, studentID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Student with ID " + studentID + " does not exist!");
            }

            connection.commit();
            ps.close();
            connection.close();
            out = true;
        } catch (SQLException var4) {
            var4.printStackTrace();
            //this.rollbackConnection();
        }
        return out;
    }

    // Inserts a new student into the database
    public boolean insertStudent(Student student) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Student VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, student.getStudentID());
            ps.setInt(2, student.getYearLevel());
            ps.setString(3, student.getFaculty());
            ps.setString(4, student.isMembership());
            ps.setString(5, student.getName());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getDietaryRestrictions());
            ps.setInt(8, student.getEventID());
            ps.setInt(9, student.getTicketID());
            ps.setInt(10, student.getFormID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Student ID: " + student.getStudentID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            //this.rollbackConnection();
            var3.printStackTrace();
        }
        return out;
    }

    // Retrieves information about all students in the database
    public Student[] getStudentInfo() {
        ArrayList<Student> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("studentID"),
                        rs.getInt("yearLevel"),
                        rs.getString("faculty"),
                        rs.getString("membership"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("dietaryRestrictions"),
                        rs.getInt("eventID"),
                        rs.getInt("ticketID"),
                        rs.getInt("formID")
                );
                result.add(student);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Student[result.size()]);
    }

    public boolean updateStudent(Student student) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            // Use a StringBuilder to dynamically build the update query
            StringBuilder query = new StringBuilder("UPDATE Student SET ");

            // Check and update each attribute if it's not null or empty
            if (student.getYearLevel() != null) {
                query.append("yearLevel = ?, ");
            }
            if (student.getFaculty() != null && !student.getFaculty().isEmpty()) {
                query.append("faculty = ?, ");
            }
            if (student.isMembership() != null) {
                query.append("membership = ?, ");
            }
            if (student.getName() != null && !student.getName().isEmpty()) {
                query.append("name = ?, ");
            }
            if (student.getEmail() != null && !student.getEmail().isEmpty()) {
                query.append("email = ?, ");
            }
            if (student.getDietaryRestrictions() != null && !student.getDietaryRestrictions().isEmpty()) {
                query.append("dietaryRestrictions = ?, ");
            }
            if (student.getEventID() != null) {
                query.append("eventID = ?, ");
            }
            if (student.getTicketID() != null) {
                query.append("ticketID = ?, ");
            }
            if (student.getFormID() != null) {
                query.append("formID = ?, ");
            }

            // Remove the trailing comma and space if any
            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            // Append the WHERE clause
            query.append(" WHERE studentID = ?");

            // Prepare the statement using the dynamic query
            PreparedStatement ps = connection.prepareStatement(query.toString());

            // Set parameters based on non-null attributes
            int parameterIndex = 1;
            if (student.getYearLevel() != null) {
                ps.setInt(parameterIndex++, student.getYearLevel());
            }
            if (student.getFaculty() != null && !student.getFaculty().isEmpty()) {
                ps.setString(parameterIndex++, student.getFaculty());
            }
            if (student.isMembership() != null) {
                ps.setString(parameterIndex++, student.isMembership());
            }
            if (student.getName() != null && !student.getName().isEmpty()) {
                ps.setString(parameterIndex++, student.getName());
            }
            if (student.getEmail() != null && !student.getEmail().isEmpty()) {
                ps.setString(parameterIndex++, student.getEmail());
            }
            if (student.getDietaryRestrictions() != null && !student.getDietaryRestrictions().isEmpty()) {
                ps.setString(parameterIndex++, student.getDietaryRestrictions());
            }
            if (student.getEventID() != null) {
                ps.setInt(parameterIndex++, student.getEventID());
            }
            if (student.getTicketID() != null) {
                ps.setInt(parameterIndex++, student.getTicketID());
            }
            if (student.getFormID() != null) {
                ps.setInt(parameterIndex++, student.getFormID());
            }

            // Set the last parameter (studentID)
            ps.setInt(parameterIndex, student.getStudentID());

            // Execute the update query
            int rowCount = ps.executeUpdate();

            // Commit the changes and close resources
            connection.commit();
            ps.close();
            connection.close();

            // Return true if at least one row was updated, indicating success
            out = rowCount > 0;
        } catch (SQLException var6) {
            System.out.println("[EXCEPTION] " + var6.getMessage());
            // Handle exceptions or log them
        }
        return out;
    }

    // Rolls back the current database connection
    public void rollbackConnection() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            connection.rollback();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    // Creates the Student table in the database and inserts sample data
    public void dataBaseStudent() {
        this.dropStudentTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Student (" +
                    "studentID INT PRIMARY KEY, " +
                    "yearLevel INT, " +
                    "faculty VARCHAR(60), " +
                    "membership CHAR(1), " +
                    "name VARCHAR(60), " +
                    "email VARCHAR(75), " +
                    "dietaryRestrictions VARCHAR(255), " +
                    "eventID INT, " +
                    "ticketID INT, " +
                    "formID INT UNIQUE, " +
                    "FOREIGN KEY (eventID) REFERENCES Event(eventID), " +
                    "FOREIGN KEY (ticketID, eventID) REFERENCES Ticket, " +
                    "FOREIGN KEY (formID) REFERENCES FeedbackForm(formID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Student student1 = new Student(1, 1, "Engineering", "Y", "John Doe", "john.doe@example.com", "None", 1, 1, 1);
        this.insertStudent(student1);

        Student student2 = new Student(2, 2, "Business", "N", "Jane Smith", "jane.smith@example.com", "Vegetarian", 2, 2, 2);
        this.insertStudent(student2);
    }

    // Drops the Student table if it exists in the database
    public void dropStudentTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("student")) {
                    stmt.execute("DROP TABLE Student");
                    break;
                }
            }

            rs.close();
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }
    }
}
