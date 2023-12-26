package com.group.eventmanager.model;

public class EventDirector {
    private Integer execID;
    private String name;
    private String team;
    private Integer eventID;
    private Integer venueID;

    // Constructors
    public EventDirector(Integer execID, String name, String team, Integer eventID, Integer venueID) {
        this.execID = execID;
        this.name = name;
        this.team = team;
        this.eventID = eventID;
        this.venueID = venueID;
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

    public Integer getEventID() {
        return eventID;
    }

    public Integer getVenueID() {
        return venueID;
    }
}
