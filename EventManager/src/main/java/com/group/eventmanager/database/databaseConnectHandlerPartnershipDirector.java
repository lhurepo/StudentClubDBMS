package com.group.eventmanager.database;

import com.group.eventmanager.model.PartnershipDirector;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerPartnershipDirector {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerPartnershipDirector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deletePartnershipDirector(int execID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PartnershipDirector WHERE execID = ?");
            ps.setInt(1, execID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Partnership Director with ID " + execID + " does not exist!");
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

    public boolean insertPartnershipDirector(PartnershipDirector partnershipDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PartnershipDirector VALUES (?,?,?)");
            ps.setInt(1, partnershipDirector.getExecID());
            ps.setString(2, partnershipDirector.getName());
            ps.setString(3, partnershipDirector.getTeam());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Partnership Director ID: " + partnershipDirector.getExecID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public PartnershipDirector[] getPartnershipDirectorInfo() {
        ArrayList<PartnershipDirector> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PartnershipDirector");

            while (rs.next()) {
                PartnershipDirector partnershipDirector = new PartnershipDirector(
                        rs.getInt("execID"),
                        rs.getString("name"),
                        rs.getString("team")
                );
                result.add(partnershipDirector);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new PartnershipDirector[result.size()]);
    }

    public boolean updatePartnershipDirector(PartnershipDirector partnershipDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE PartnershipDirector SET ");

            if (partnershipDirector.getName() != null && !partnershipDirector.getName().isEmpty()) {
                query.append("name = ?, ");
            }
            if (partnershipDirector.getTeam() != null && !partnershipDirector.getTeam().isEmpty()) {
                query.append("team = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE execID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (partnershipDirector.getName() != null && !partnershipDirector.getName().isEmpty()) {
                ps.setString(parameterIndex++, partnershipDirector.getName());
            }
            if (partnershipDirector.getTeam() != null && !partnershipDirector.getTeam().isEmpty()) {
                ps.setString(parameterIndex++, partnershipDirector.getTeam());
            }

            ps.setInt(parameterIndex, partnershipDirector.getExecID());

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

    public void dataBasePartnershipDirector() {
        this.dropPartnershipDirectorTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE PartnershipDirector (" +
                    "execID INT PRIMARY KEY NOT NULL, " +
                    "name VARCHAR(60), " +
                    "team VARCHAR(60)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        PartnershipDirector pd1 = new PartnershipDirector(1, "Partnership Director One", "Team X");
        this.insertPartnershipDirector(pd1);
        PartnershipDirector pd2 = new PartnershipDirector(2, "Partnership Director Two", "Team Y");
        this.insertPartnershipDirector(pd2);
    }

    public void dropPartnershipDirectorTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("partnershipdirector")) {
                    stmt.execute("DROP TABLE PartnershipDirector");
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
