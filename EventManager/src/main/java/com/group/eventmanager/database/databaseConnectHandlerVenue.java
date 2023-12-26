package com.group.eventmanager.database;

import com.group.eventmanager.model.Venue;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerVenue {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username;
    private String password;

    public databaseConnectHandlerVenue(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteVenue(int venueID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Venue WHERE venueID = ?");
            ps.setInt(1, venueID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Venue with ID " + venueID + " does not exist!");
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

    public boolean insertVenue(Venue venue) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Venue VALUES (?,?,?,?)");
            ps.setInt(1, venue.getVenueID());
            ps.setString(2, venue.getAddress());
            ps.setInt(3, venue.getRoomNumber());
            ps.setInt(4, venue.getCapacity());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Venue ID: " + venue.getVenueID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public Venue[] getVenueInfo() {
        ArrayList<Venue> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Venue");

            while (rs.next()) {
                Venue venue = new Venue(
                        rs.getInt("venueID"),
                        rs.getString("address"),
                        rs.getInt("roomNumber"),
                        rs.getInt("capacity")
                );
                result.add(venue);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Venue[result.size()]);
    }

    public boolean updateVenue(Venue venue) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE Venue SET ");

            if (venue.getAddress() != null && !venue.getAddress().isEmpty()) {
                query.append("address = ?, ");
            }
            if (venue.getRoomNumber() != null) {
                query.append("roomNumber = ?, ");
            }
            if (venue.getCapacity() != null) {
                query.append("capacity = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE venueID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (venue.getAddress() != null && !venue.getAddress().isEmpty()) {
                ps.setString(parameterIndex++, venue.getAddress());
            }
            if (venue.getRoomNumber() != null) {
                ps.setInt(parameterIndex++, venue.getRoomNumber());
            }
            if (venue.getCapacity() != null) {
                ps.setInt(parameterIndex++, venue.getCapacity());
            }

            ps.setInt(parameterIndex, venue.getVenueID());

            int rowCount = ps.executeUpdate();

            connection.commit();
            ps.close();
            connection.close();

            out = rowCount > 0;
        } catch (SQLException var6) {
            System.out.println("[EXCEPTION] " + var6.getMessage());
        }
        return out;
    }

    public void rollbackConnection() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            connection.rollback();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    public void dataBaseVenue() {
        this.dropVenueTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Venue (" +
                    "venueID INT PRIMARY KEY, " +
                    "address VARCHAR(255), " +
                    "roomNumber INT, " +
                    "capacity INT" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Venue v1 = new Venue(1, "Address One", 101, 200);
        this.insertVenue(v1);
        Venue v2 = new Venue(2, "Address Two", 201, 300);
        this.insertVenue(v2);
    }

    public void dropVenueTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("venue")) {
                    stmt.execute("DROP TABLE Venue");
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
