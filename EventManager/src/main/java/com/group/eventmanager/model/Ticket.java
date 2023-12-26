package com.group.eventmanager.model;

public class Ticket {
    private Integer ticketID;
    private Integer eventID;
    private Double price;

    // Constructors
    public Ticket(Integer ticketID, Integer eventID, Double price) {
        this.ticketID = ticketID;
        this.eventID = eventID;
        this.price = price;
    }

    // Getter methods
    public Integer getTicketID() {
        return ticketID;
    }

    public Integer getEventID() {
        return eventID;
    }

    public Double getPrice() {
        return price;
    }
}
