package com.group.eventmanager.model;

import java.sql.Date;
import java.sql.Time;

public class Event {
    private Integer eventID;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private Integer venueID;

    // Constructors
    public Event(Integer eventID, String eventName, Date eventDate, Time eventTime, Integer venueID) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venueID = venueID;
    }

    // Getter methods
    public Integer getEventID() {
        return eventID;
    }

    public String getEventName() { return eventName; }

    public Date getEventDate() {
        return eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public Integer getVenueID() {
        return venueID;
    }
}
