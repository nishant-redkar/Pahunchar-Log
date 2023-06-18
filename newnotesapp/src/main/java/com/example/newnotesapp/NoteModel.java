package com.example.newnotesapp;

import java.io.Serializable;

public class NoteModel implements Serializable {

    private int id;
    private String noteTitle;
    private String noteDetails;
    private String noteDate;
    private String noteTime;
    private boolean pinned; // Added pinned field

    public NoteModel() {
    }

    public NoteModel(String noteTitle, String noteDetails, String noteDate, String noteTime) {
        this.noteTitle = noteTitle;
        this.noteDetails = noteDetails;
        this.noteDate = noteDate;
        this.noteTime = noteTime;
    }

    public NoteModel(int id, String noteTitle, String noteDetails, String noteDate, String noteTime) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteDetails = noteDetails;
        this.noteDate = noteDate;
        this.noteTime = noteTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDetails() {
        return noteDetails;
    }

    public void setNoteDetails(String noteDetails) {
        this.noteDetails = noteDetails;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }

    public boolean isPinned() { // Getter for pinned field
        return pinned;
    }

    public void setPinned(boolean pinned) { // Setter for pinned field
        this.pinned = pinned;
    }
}
