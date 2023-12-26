package com.group.eventmanager.model;

public class PartnershipDirector {
    private Integer execID;
    private String name;
    private String team;

    // Constructors
    public PartnershipDirector(Integer execID, String name, String team) {
        this.execID = execID;
        this.name = name;
        this.team = team;
    }

    // Getter methods
    public Integer getExecID() {
        return execID;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}
