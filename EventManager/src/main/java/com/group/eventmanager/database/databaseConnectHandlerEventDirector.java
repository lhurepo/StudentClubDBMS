package com.group.eventmanager.database;

import com.group.eventmanager.model.EventDirector;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerEventDirector {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerEventDirector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteEventDirector(int execID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM EventDirector WHERE execID = ?");
            ps.setInt(1, execID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Event Director with ID " + execID + " does not exist!");
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

    public boolean insertEventDirector(EventDirector eventDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO EventDirector VALUES (?,?,?,?,?)");
            ps.setInt(1, eventDirector.getExecID());
            ps.setString(2, eventDirector.getName());
            ps.setString(3, eventDirector.getTeam());
            ps.setInt(4, eventDirector.getEventID());
            ps.setInt(5, eventDirector.getVenueID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Event Director ID: " + eventDirector.getExecID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public EventDirector[] getEventDirectorInfo() {
        ArrayList<EventDirector> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EventDirector");

            while (rs.next()) {
                EventDirector eventDirector = new EventDirector(
                        rs.getInt("execID"),
                        rs.getString("name"),
                        rs.getString("team"),
                        rs.getInt("eventID"),
                        rs.getInt("venueID")
                );
                result.add(eventDirector);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new EventDirector[result.size()]);
    }

    public boolean updateEventDirector(EventDirector eventDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE EventDirector SET ");
            if (eventDirector.getName() != null && !eventDirector.getName().isEmpty()) {
                query.append("name = ?, ");
            }
            if (eventDirector.getTeam() != null) {
                query.append("team = ?, ");
            }
            if (eventDirector.getEventID() != 0) {
                query.append("eventID = ?, ");
            }
            if (eventDirector.getVenueID() != 0) {
                query.append("venueID = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE execID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (eventDirector.getName() != null && !eventDirector.getName().isEmpty()) {
                ps.setString(parameterIndex++, eventDirector.getName());
            }
            if (eventDirector.getTeam() != null) {
                ps.setString(parameterIndex++, eventDirector.getTeam());
            }
            if (eventDirector.getEventID() != 0) {
                ps.setInt(parameterIndex++, eventDirector.getEventID());
            }
            if (eventDirector.getVenueID() != 0) {
                ps.setInt(parameterIndex++, eventDirector.getVenueID());
            }

            ps.setInt(parameterIndex, eventDirector.getExecID());

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

    public void dataBaseEventDirector() {
        this.dropEventDirectorTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE EventDirector (" +
                    "execID INT PRIMARY KEY, " +
                    "name VARCHAR(60), " +
                    "team VARCHAR(60), " +
                    "eventID INT UNIQUE, " +
                    "venueID INT UNIQUE, " +
                    "FOREIGN KEY (eventID) REFERENCES Event(eventID), " +
                    "FOREIGN KEY (venueID) REFERENCES Venue(venueID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        EventDirector e1 = new EventDirector(1, "Director One", "Team A", 1, 1);
        this.insertEventDirector(e1);
        EventDirector e2 = new EventDirector(2, "Director Two", "Team B", 2, 2);
        this.insertEventDirector(e2);
    }

    public void dropEventDirectorTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("eventdirector")) {
                    stmt.execute("DROP TABLE EventDirector");
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
