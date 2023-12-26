package com.group.eventmanager.database;

import com.group.eventmanager.model.Ticket;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerTicket {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerTicket(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteTicket(int ticketID, int eventID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Ticket WHERE ticketID = ? AND eventID = ?");
            ps.setInt(1, ticketID);
            ps.setInt(2, eventID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Ticket with ID " + ticketID + " for Event ID " + eventID + " does not exist!");
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

    public boolean insertTicket(Ticket ticket) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Ticket VALUES (?,?,?)");
            ps.setInt(1, ticket.getTicketID());
            ps.setInt(2, ticket.getEventID());
            ps.setDouble(3, ticket.getPrice());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Ticket ID: " + ticket.getTicketID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public Ticket[] getTicketInfo() {
        ArrayList<Ticket> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Ticket");

            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("ticketID"),
                        rs.getInt("eventID"),
                        rs.getDouble("price")
                );
                result.add(ticket);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Ticket[result.size()]);
    }

    public boolean updateTicket(Ticket ticket) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("UPDATE Ticket SET price = ? WHERE ticketID = ? AND eventID = ?");
            ps.setDouble(1, ticket.getPrice());
            ps.setInt(2, ticket.getTicketID());
            ps.setInt(3, ticket.getEventID());
            int rowCount = ps.executeUpdate();

            connection.commit();
            ps.close();
            connection.close();
            out = rowCount > 0;
        } catch (SQLException var4) {
            var4.printStackTrace();
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

    public void dataBaseTicket() {
        this.dropTicketTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Ticket (" +
                    "ticketID INT NOT NULL, " +
                    "eventID INT NOT NULL, " +
                    "price NUMBER(4, 2), " +
                    "PRIMARY KEY (ticketID, eventID), " +
                    "FOREIGN KEY (eventID) REFERENCES Event(eventID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Ticket t1 = new Ticket(1, 1, 20.00);
        this.insertTicket(t1);
        Ticket t2 = new Ticket(2, 1, 15.00);
        this.insertTicket(t2);
    }

    public void dropTicketTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM user_tables");

            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase("Ticket")) {
                    stmt.execute("DROP TABLE Ticket");
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
