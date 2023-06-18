package com.example.eventtracker;

import java.io.Serializable;

public class EventModel implements Serializable {

    private int id;
    private String eventTitle;
    private String eventDetails;
    private String eventDate;
    private boolean pinned; // Added pinned field

    public EventModel() {
    }

    public EventModel(String eventTitle, String eventDetails, String eventDate) {
        this.eventTitle = eventTitle;
        this.eventDetails = eventDetails;
        this.eventDate = eventDate;
    }

    public EventModel(int id, String eventTitle, String eventDetails, String eventDate) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventDetails = eventDetails;
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public boolean isPinned() { // Getter for pinned field
        return pinned;
    }

    public void setPinned(boolean pinned) { // Setter for pinned field
        this.pinned = pinned;
    }
}
