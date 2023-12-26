package com.group.eventmanager.database;

import java.sql.*;
import java.util.ArrayList;
import com.group.eventmanager.model.Infographic;

public class databaseConnectHandlerInfographic {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerInfographic(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteInfographic(String filename) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Infographic WHERE filename = ?");
            ps.setString(1, filename);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Infographic with filename " + filename + " does not exist!");
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

    public boolean insertInfographic(Infographic infographic) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Infographic VALUES (?,?,?,?)");
            ps.setString(1, infographic.getFilename());
            ps.setString(2, infographic.getEditingSoftware());
            ps.setInt(3, infographic.getExecID());
            ps.setInt(4, infographic.getEventID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Infographic filename: " + infographic.getFilename());
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public Infographic[] getInfographicInfo() {
        ArrayList<Infographic> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Infographic");

            while (rs.next()) {
                Infographic infographic = new Infographic(
                        rs.getString("filename"),
                        rs.getString("editingSoftware"),
                        rs.getInt("execID"),
                        rs.getInt("eventID")
                );
                result.add(infographic);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Infographic[result.size()]);
    }

    public boolean updateInfographic(Infographic infographic) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE Infographic SET ");

            if (infographic.getEditingSoftware() != null && !infographic.getEditingSoftware().isEmpty()) {
                query.append("editingSoftware = ?, ");
            }
            if (infographic.getExecID() != null) {
                query.append("execID = ?, ");
            }
            if (infographic.getEventID() != null) {
                query.append("eventID = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE filename = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (infographic.getEditingSoftware() != null && !infographic.getEditingSoftware().isEmpty()) {
                ps.setString(parameterIndex++, infographic.getEditingSoftware());
            }
            if (infographic.getExecID() != null) {
                ps.setInt(parameterIndex++, infographic.getExecID());
            }
            if (infographic.getEventID() != null) {
                ps.setInt(parameterIndex++, infographic.getEventID());
            }

            ps.setString(parameterIndex, infographic.getFilename());

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

    public void dataBaseInfographic() {
        this.dropInfographicTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Infographic (" +
                    "filename VARCHAR(255) PRIMARY KEY, " +
                    "editingSoftware VARCHAR(255), " +
                    "execID INT, " +
                    "eventID INT, " +
                    "FOREIGN KEY (execID) REFERENCES MarketingDirector(execID), " +
                    "FOREIGN KEY (eventID) REFERENCES Event(eventID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Infographic i1 = new Infographic("info1.png", "Photoshop", 1, 1);
        this.insertInfographic(i1);
        Infographic i2 = new Infographic("info2.jpg", "Illustrator", 2, 2);
        this.insertInfographic(i2);
    }

    public void dropInfographicTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("infographic")) {
                    stmt.execute("DROP TABLE Infographic");
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
