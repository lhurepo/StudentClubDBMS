package com.group.eventmanager.model;

public class Delegate {
    private Integer employeeID;
    private String company;
    private String position;
    private String name;
    private String email;
    private String dietaryRestrictions;
    private String LinkedIn;
    private Integer eventID;
    private Integer execID;

    // Constructors
    public Delegate(Integer employeeID, String company, String position, String name, String email,
                    String dietaryRestrictions, String LinkedIn, Integer eventID, Integer execID) {
        this.employeeID = employeeID;
        this.company = company;
        this.position = position;
        this.name = name;
        this.email = email;
        this.dietaryRestrictions = dietaryRestrictions;
        this.LinkedIn = LinkedIn;
        this.eventID = eventID;
        this.execID = execID;
    }

    // Getter methods
    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public Integer getEventID() {
        return eventID;
    }

    public Integer getExecID() {
        return execID;
    }
}
