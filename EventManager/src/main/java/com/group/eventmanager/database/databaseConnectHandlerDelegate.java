package com.group.eventmanager.database;

import com.group.eventmanager.model.Delegate;

import java.sql.*;
import java.util.ArrayList;

// Handles the database connection for the Delegate entity as well as CRUD operations
public class databaseConnectHandlerDelegate {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    // Constructor to initialize the database connection
    public databaseConnectHandlerDelegate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Deletes a delegate from the database based on the employeeID
    public boolean deleteDelegate(int employeeID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Delegate WHERE employeeID = ?");
            ps.setInt(1, employeeID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Delegate with ID " + employeeID + " does not exist!");
            }

            connection.commit();
            ps.close();
            connection.close();
            out = true;
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
        return out;
    }

    // Inserts a new delegate into the database
    public boolean insertDelegate(Delegate delegate) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Delegate VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, delegate.getEmployeeID());
            ps.setString(2, delegate.getCompany());
            ps.setString(3, delegate.getPosition());
            ps.setString(4, delegate.getName());
            ps.setString(5, delegate.getEmail());
            ps.setString(6, delegate.getDietaryRestrictions());
            ps.setString(7, delegate.getLinkedIn());
            ps.setInt(8, delegate.getEventID());
            ps.setInt(9, delegate.getExecID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Delegate ID: " + delegate.getEmployeeID());
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    // Retrieves information about all delegates in the database
    public Delegate[] getDelegateInfo() {
        ArrayList<Delegate> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Delegate");

            while (rs.next()) {
                Delegate delegate = new Delegate(
                        rs.getInt("employeeID"),
                        rs.getString("company"),
                        rs.getString("position"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("dietaryRestrictions"),
                        rs.getString("LinkedIn"),
                        rs.getInt("eventID"),
                        rs.getInt("execID")
                );
                result.add(delegate);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Delegate[result.size()]);
    }

    public boolean updateDelegate(Delegate delegate) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            // Use a StringBuilder to dynamically build the update query
            StringBuilder query = new StringBuilder("UPDATE Delegate SET ");

            // Check and update each attribute if it's not null or empty
            if (delegate.getCompany() != null && !delegate.getCompany().isEmpty()) {
                query.append("company = ?, ");
            }
            if (delegate.getPosition() != null && !delegate.getPosition().isEmpty()) {
                query.append("position = ?, ");
            }
            if (delegate.getName() != null && !delegate.getName().isEmpty()) {
                query.append("name = ?, ");
            }
            if (delegate.getEmail() != null && !delegate.getEmail().isEmpty()) {
                query.append("email = ?, ");
            }
            if (delegate.getDietaryRestrictions() != null && !delegate.getDietaryRestrictions().isEmpty()) {
                query.append("dietaryRestrictions = ?, ");
            }
            if (delegate.getLinkedIn() != null && !delegate.getLinkedIn().isEmpty()) {
                query.append("LinkedIn = ?, ");
            }
            if (delegate.getEventID() != null) {
                query.append("eventID = ?, ");
            }
            if (delegate.getExecID() != null) {
                query.append("execID = ?, ");
            }

            // Remove the trailing comma and space if any
            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            // Append the WHERE clause
            query.append(" WHERE employeeID = ?");

            // Prepare the statement using the dynamic query
            PreparedStatement ps = connection.prepareStatement(query.toString());

            // Set parameters based on non-null attributes
            int parameterIndex = 1;
            if (delegate.getCompany() != null && !delegate.getCompany().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getCompany());
            }
            if (delegate.getPosition() != null && !delegate.getPosition().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getPosition());
            }
            if (delegate.getName() != null && !delegate.getName().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getName());
            }
            if (delegate.getEmail() != null && !delegate.getEmail().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getEmail());
            }
            if (delegate.getDietaryRestrictions() != null && !delegate.getDietaryRestrictions().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getDietaryRestrictions());
            }
            if (delegate.getLinkedIn() != null && !delegate.getLinkedIn().isEmpty()) {
                ps.setString(parameterIndex++, delegate.getLinkedIn());
            }
            if (delegate.getEventID() != null) {
                ps.setInt(parameterIndex++, delegate.getEventID());
            }
            if (delegate.getExecID() != null) {
                ps.setInt(parameterIndex++, delegate.getExecID());
            }

            // Set the last parameter (employeeID)
            ps.setInt(parameterIndex, delegate.getEmployeeID());

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

    // Creates the Delegate table in the database and inserts sample data
    public void dataBaseDelegate() {
        this.dropDelegateTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Delegate (" +
                    "employeeID INT PRIMARY KEY, " +
                    "company VARCHAR(60), " +
                    "position VARCHAR(60), " +
                    "name VARCHAR(60), " +
                    "email VARCHAR(75), " +
                    "dietaryRestrictions VARCHAR(255), " +
                    "LinkedIn VARCHAR(255), " +
                    "eventID INT, " +
                    "execID INT UNIQUE, " +
                    "FOREIGN KEY (eventID) REFERENCES Event(eventID), " +
                    "FOREIGN KEY (execID) REFERENCES PartnershipDirector(execID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Delegate delegate1 = new Delegate(1, "Company One", "Manager", "John Delegate", "john.delegate@example.com", "None", "linkedin.com/johndelegate", 1, 1);
        this.insertDelegate(delegate1);

        Delegate delegate2 = new Delegate(2, "Company Two", "Director", "Jane Delegate", "jane.delegate@example.com", "Vegetarian", "linkedin.com/janedelagate", 2, 2);
        this.insertDelegate(delegate2);
    }

    // Drops the Delegate table if it exists in the database
    public void dropDelegateTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("delegate")) {
                    stmt.execute("DROP TABLE Delegate");
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
