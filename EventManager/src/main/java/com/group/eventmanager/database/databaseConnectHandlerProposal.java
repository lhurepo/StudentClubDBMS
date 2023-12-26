package com.group.eventmanager.database;

import java.sql.*;
import java.util.ArrayList;

import com.group.eventmanager.model.Proposal;

public class databaseConnectHandlerProposal {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private String username = null;
    private String password = null;

    public databaseConnectHandlerProposal(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean deleteProposal(int proposalID) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Proposal WHERE proposalID = ?");
            ps.setInt(1, proposalID);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("[WARNING] Proposal with ID " + proposalID + " does not exist!");
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

    public boolean insertProposal(Proposal proposal) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Proposal VALUES (?,?,?)");
            ps.setInt(1, proposal.getProposalID());
            ps.setString(2, proposal.getProposalText());
            ps.setInt(3, proposal.getExecID());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            connection.close();
            System.out.println("Insert successful for Proposal ID: " + proposal.getProposalID());
            out = true;
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
        return out;
    }

    public Proposal[] getProposalInfo() {
        ArrayList<Proposal> result = new ArrayList();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Proposal");

            while (rs.next()) {
                Proposal proposal = new Proposal(
                        rs.getInt("proposalID"),
                        rs.getString("proposalText"),
                        rs.getInt("execID")
                );
                result.add(proposal);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return result.toArray(new Proposal[result.size()]);
    }

    public boolean updateProposal(Proposal proposal) {
        boolean out = false;
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            StringBuilder query = new StringBuilder("UPDATE Proposal SET ");

            if (proposal.getProposalText() != null && !proposal.getProposalText().isEmpty()) {
                query.append("proposalText = ?, ");
            }
            if (proposal.getExecID() != null) {
                query.append("execID = ?, ");
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.setLength(query.length() - 2);
            }

            query.append(" WHERE proposalID = ?");

            PreparedStatement ps = connection.prepareStatement(query.toString());

            int parameterIndex = 1;
            if (proposal.getProposalText() != null && !proposal.getProposalText().isEmpty()) {
                ps.setString(parameterIndex++, proposal.getProposalText());
            }
            if (proposal.getExecID() != null) {
                ps.setInt(parameterIndex++, proposal.getExecID());
            }

            ps.setInt(parameterIndex, proposal.getProposalID());

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

    public void dataBaseProposal() {
        this.dropProposalTableIfExists();

        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Proposal (" +
                    "proposalID INT PRIMARY KEY NOT NULL, " +
                    "proposalText VARCHAR(800), " +
                    "execID INT, " +
                    "FOREIGN KEY (execID) REFERENCES PartnershipDirector(execID)" +
                    ")");
            stmt.close();
            connection.commit();
            connection.close();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        Proposal p1 = new Proposal(1, "Proposal One Text", 1);
        this.insertProposal(p1);
        Proposal p2 = new Proposal(2, "Proposal Two Text", 2);
        this.insertProposal(p2);
    }

    public void dropProposalTableIfExists() {
        try {
            Connection connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while (rs.next()) {
                if (rs.getString(1).toLowerCase().equals("proposal")) {
                    stmt.execute("DROP TABLE Proposal");
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
