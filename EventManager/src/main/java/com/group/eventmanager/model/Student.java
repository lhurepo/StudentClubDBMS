package com.group.eventmanager.model;

public class Student {
    private Integer studentID;
    private Integer yearLevel;
    private String faculty;
    private String membership;
    private String name;
    private String email;
    private String dietaryRestrictions;
    private Integer eventID;
    private Integer ticketID;
    private Integer formID;

    // Constructors
    public Student(Integer studentID, Integer yearLevel, String faculty, String membership, String name,
                   String email, String dietaryRestrictions, Integer eventID, Integer ticketID, Integer formID) {
        this.studentID = studentID;
        this.yearLevel = yearLevel;
        this.faculty = faculty;
        this.membership = membership;
        this.name = name;
        this.email = email;
        this.dietaryRestrictions = dietaryRestrictions;
        this.eventID = eventID;
        this.ticketID = ticketID;
        this.formID = formID;
    }

    // Getter methods
    public Integer getStudentID() {
        return studentID;
    }

    public Integer getYearLevel() {
        return yearLevel;
    }

    public String getFaculty() {
        return faculty;
    }

    public String isMembership() {
        return membership;
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

    public Integer getEventID() {
        return eventID;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public Integer getFormID() {
        return formID;
    }
}
