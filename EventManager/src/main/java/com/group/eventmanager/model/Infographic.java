package com.group.eventmanager.model;

public class Infographic {
    private String filename;
    private String editingSoftware;
    private Integer execID;
    private Integer eventID;

    // Constructors
    public Infographic(String filename, String editingSoftware, Integer execID, Integer eventID) {
        this.filename = filename;
        this.editingSoftware = editingSoftware;
        this.execID = execID;
        this.eventID = eventID;
    }

    // Getter methods
    public String getFilename() {
        return filename;
    }

    public String getEditingSoftware() {
        return editingSoftware;
    }

    public Integer getExecID() {
        return execID;
    }

    public Integer getEventID() {
        return eventID;
    }
}
