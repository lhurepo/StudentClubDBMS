package com.group.eventmanager.database;


import com.group.eventmanager.model.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles the database connection for the updated Event entity as well as CRUD operations
public class databaseConnectHandlerEvent {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    public String username = null;
    private String password = null;

    // Constructor to initialize the database connection
    public databaseConnectHandlerEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Deletes an event from the database based on the eventID
    public boolean deleteEvent(int eventID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Event WHERE eventID = ?");
            ps.setInt(1, eventID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Event with ID " + eventID + " does not exist!");
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

    // Inserts a new event into the database
    public boolean insertEvent(Event event) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO EVENT VALUES (?,?,?,?,?)");
            ps.setInt(1, event.getEventID());
            ps.setString(2, event.getEventName());
            ps.setDate(3, event.getEventDate());
            ps.setTime(4, event.getEventTime());
            ps.setInt(5, event.getVenueID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Event ID: " + event.getEventID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            //this.rollbackConnection();
            var3.printStackTrace();
        }
        return out;
    }

    // Retrieves information about all events in the database
    public Event[] getEventInfo() {
        ArrayList<Event> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Event");

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("eventID"),
                        rs.getString("eventName"),
                        rs.getDate("eventDate"),
                        rs.getTime("eventTime"),
                        rs.getInt("venueID")
                );
                result.add(event);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Event[result.size()]);
    }

    public boolean updateEvent(Event event) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            // Use a StringBuilder to dynamically build the update query
            StringBuilder query = new StringBuilder("UPDATE Event SET ");

            // Check and update each attribute if it's not null or empty
            if (event.getEventName() != null && !event.getEventName().isEmpty()) {
                query.append("eventName = ?, ");
            }
            if (event.getEventDate() != null) {
                query.append("eventDate = ?, ");
            }
            if (event.getEventTime() != null) {
                query.append("eventTime = ?, ");
            }
            if (event.getVenueID() != null) {
                query.append("venueID = ?, ");
            }

            // Remove the trailing comma and space if any
            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            // Append the WHERE clause
            query.append(" WHERE eventID = ?");

            // Prepare the statement using the dynamic query
            PreparedStatement ps = connection.prepareStatement(query.toString());

            // Set parameters based on non-null attributes
            int parameterIndex = 1;
            if (event.getEventName() != null && !event.getEventName().isEmpty()) {
                ps.setString(parameterIndex++, event.getEventName());
            }
            if (event.getEventDate() != null) {
                ps.setDate(parameterIndex++, event.getEventDate());
            }
            if (event.getEventTime() != null) {
                ps.setTime(parameterIndex++, event.getEventTime());
            }
            if (event.getVenueID() != null) {
                ps.setInt(parameterIndex++, event.getVenueID());
            }

            // Set the last parameter (eventID)
            ps.setInt(parameterIndex, event.getEventID());

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

    // Creates the Event table in the database and inserts sample data
    public void dataBaseEvent() {
        this.dropEventTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Event (" +
                    "eventID INT PRIMARY KEY, " +
                    "eventName VARCHAR(60), " +
                    "eventDate DATE, " +
                    "eventTime TIME, " +
                    "venueID INT UNIQUE, " +
                    "FOREIGN KEY (venueID) REFERENCES Venue(venueID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Event e1 = new Event(1, "Event One", Date.valueOf("2023-01-01"), Time.valueOf("12:00:00"), 1);
        this.insertEvent(e1);
        Event e2 = new Event(2, "Event Two", Date.valueOf("2023-02-02"), Time.valueOf("15:30:00"), 2);
        this.insertEvent(e2);
    }

    // Drops the Event table if it exists in the database
    public void dropEventTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("event")) {
                    stmt.execute("DROP TABLE Event");
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
