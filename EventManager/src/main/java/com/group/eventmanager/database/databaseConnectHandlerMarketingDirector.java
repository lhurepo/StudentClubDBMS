package com.group.eventmanager.database;

import com.group.eventmanager.model.MarketingDirector;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerMarketingDirector {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username;
    private String password;

    public databaseConnectHandlerMarketingDirector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteMarketingDirector(int execID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM MarketingDirector WHERE execID = ?");
            ps.setInt(1, execID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Marketing Director with ID " + execID + " does not exist!");
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

    public boolean insertMarketingDirector(MarketingDirector marketingDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO MarketingDirector VALUES (?,?,?)");
            ps.setInt(1, marketingDirector.getExecID());
            ps.setString(2, marketingDirector.getName());
            ps.setString(3, marketingDirector.getTeam());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Marketing Director ID: " + marketingDirector.getExecID());
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public MarketingDirector[] getMarketingDirectorInfo() {
        ArrayList<MarketingDirector> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MarketingDirector");

            while (rs.next()) {
                MarketingDirector marketingDirector = new MarketingDirector(
                        rs.getInt("execID"),
                        rs.getString("name"),
                        rs.getString("team")
                );
                result.add(marketingDirector);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new MarketingDirector[result.size()]);
    }

    public boolean updateMarketingDirector(MarketingDirector marketingDirector) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE MarketingDirector SET ");

            if (marketingDirector.getName() != null && !marketingDirector.getName().isEmpty()) {
                query.append("name = ?, ");
            }
            if (marketingDirector.getTeam() != null && !marketingDirector.getTeam().isEmpty()) {
                query.append("team = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE execID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (marketingDirector.getName() != null && !marketingDirector.getName().isEmpty()) {
                ps.setString(parameterIndex++, marketingDirector.getName());
            }
            if (marketingDirector.getTeam() != null && !marketingDirector.getTeam().isEmpty()) {
                ps.setString(parameterIndex++, marketingDirector.getTeam());
            }

            ps.setInt(parameterIndex, marketingDirector.getExecID());

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

    public void dataBaseMarketingDirector() {
        this.dropMarketingDirectorTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE MarketingDirector (" +
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

        MarketingDirector md1 = new MarketingDirector(1, "Director One", "Marketing Team A");
        this.insertMarketingDirector(md1);
        MarketingDirector md2 = new MarketingDirector(2, "Director Two", "Marketing Team B");
        this.insertMarketingDirector(md2);
    }

    public void dropMarketingDirectorTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("marketingdirector")) {
                    stmt.execute("DROP TABLE MarketingDirector");
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
