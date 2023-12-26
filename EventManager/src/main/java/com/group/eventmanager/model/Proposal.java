package com.group.eventmanager.model;

public class Proposal {
    private Integer proposalID;
    private String proposalText;
    private Integer execID;

    // Constructors
    public Proposal(Integer proposalID, String proposalText, Integer execID) {
        this.proposalID = proposalID;
        this.proposalText = proposalText;
        this.execID = execID;
    }

    // Getter methods
    public Integer getProposalID() {
        return proposalID;
    }

    public String getProposalText() {
        return proposalText;
    }

    public Integer getExecID() {
        return execID;
    }
}
