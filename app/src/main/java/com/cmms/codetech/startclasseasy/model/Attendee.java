package com.cmms.codetech.startclasseasy.model;

/**
 * Created by daryl on 28/10/2015.
 */
public class Attendee {
    String attendeeName;
    String attendeeID;
    String attendeeEmail;
    String attendeeContact;
    Long rowID;
    int checked;
    Boolean selected;

    public Attendee(Long rowID) {
        super();
        this.rowID = rowID;
    }

    public Attendee(String attendeeName, String attendeeID, String attendeeEmail, String attendeeContact, Long rowID) {
        super();
        this.attendeeName = attendeeName;
        this.attendeeID = attendeeID;
        this.attendeeEmail = attendeeEmail;
        this.attendeeContact = attendeeContact;
        this.rowID = rowID;
    }

    public Attendee(String attendeeName, String attendeeID, String attendeeEmail, String attendeeContact, Long rowID, Boolean selected) {
        super();
        this.attendeeName = attendeeName;
        this.attendeeID = attendeeID;
        this.attendeeEmail = attendeeEmail;
        this.attendeeContact = attendeeContact;
        this.rowID = rowID;
        this.selected = selected;
    }

    public Attendee(String attendeeName, String attendeeID, String attendeeEmail, String attendeeContact, Long rowID, int checked) {
        super();
        this.attendeeName = attendeeName;
        this.attendeeID = attendeeID;
        this.attendeeEmail = attendeeEmail;
        this.attendeeContact = attendeeContact;
        this.rowID = rowID;
        this.checked = checked;
    }



    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public String getAttendeeID() {
        return attendeeID;
    }

    public void setAttendeeID(String attendeeID) {
        this.attendeeID = attendeeID;
    }

    public String getAttendeeEmail() {
        return attendeeEmail;
    }

    public void setAttendeeEmail(String attendeeEmail) {
        this.attendeeEmail = attendeeEmail;
    }

    public String getAttendeeContact() {
        return attendeeContact;
    }

    public void setAttendeeContact(String attendeeContact) {
        this.attendeeContact = attendeeContact;
    }

    public Long getRowID() {
        return rowID;
    }

    public void setRowID(Long rowID) {
        this.rowID = rowID;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
