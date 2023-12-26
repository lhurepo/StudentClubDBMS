package com.group.eventmanager.database;

import com.group.eventmanager.model.FeedbackForm;

import java.sql.*;
import java.util.ArrayList;

public class databaseConnectHandlerFeedbackForm {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerFeedbackForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteFeedbackForm(int formID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM FeedbackForm WHERE formID = ?");
            ps.setInt(1, formID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] FeedbackForm with ID " + formID + " does not exist!");
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

    public boolean insertFeedbackForm(FeedbackForm feedbackForm) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO FeedbackForm VALUES (?)");
            ps.setInt(1, feedbackForm.getFormID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for FeedbackForm ID: " + feedbackForm.getFormID()); // Remove later
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public FeedbackForm[] getFeedbackFormInfo() {
        ArrayList<FeedbackForm> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FeedbackForm");

            while (rs.next()) {
                FeedbackForm feedbackForm = new FeedbackForm(
                        rs.getInt("formID")
                );
                result.add(feedbackForm);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new FeedbackForm[result.size()]);
    }

    public boolean updateFeedbackForm(FeedbackForm feedbackForm) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE FeedbackForm SET ");

            if (feedbackForm.getFormID() != null) {
                query.append("formID = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE formID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;

            if (feedbackForm.getFormID() != null) {
                ps.setInt(parameterIndex++, feedbackForm.getFormID());
            }

            ps.setInt(parameterIndex, feedbackForm.getFormID());

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

    public void dataBaseFeedbackForm() {
        this.dropFeedbackFormTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE FeedbackForm (" +
                    "formID INT PRIMARY KEY" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        FeedbackForm f1 = new FeedbackForm(1);
        this.insertFeedbackForm(f1);
        FeedbackForm f2 = new FeedbackForm(2);
        this.insertFeedbackForm(f2);
    }

    public void dropFeedbackFormTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("feedbackform")) {
                    stmt.execute("DROP TABLE FeedbackForm");
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
