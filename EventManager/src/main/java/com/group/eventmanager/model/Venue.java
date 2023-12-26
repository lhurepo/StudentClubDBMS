package com.group.eventmanager.model;

public class Venue {
    private Integer venueID;
    private String address;
    private Integer roomNumber;
    private Integer capacity;

    // Constructors
    public Venue(Integer venueID, String address, Integer roomNumber, Integer capacity) {
        this.venueID = venueID;
        this.address = address;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    // Getter methods
    public Integer getVenueID() {
        return venueID;
    }

    public String getAddress() {
        return address;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

}
