package com.example.ameerak.calendarproject_team4.business_objects_layer;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Event implements Serializable, Comparable<Event> {

    private final SimpleDateFormat mSdfParser = new SimpleDateFormat("MMMM d, yyyy h:mm a");

    private UUID mEventId;
    private String mTitle;
    private GregorianCalendar mEventStartTime;
    private GregorianCalendar mEventEndTime;
    private String mLocation;
    private String mDescription;

    public Event() {
        mEventId = UUID.randomUUID();
        mTitle = "";
        mEventStartTime =
                (GregorianCalendar) Calendar.getInstance();
        mEventEndTime =
                (GregorianCalendar) Calendar.getInstance();
        mLocation = "";
        mDescription = "";
    }

    public Event(String title, GregorianCalendar eventStartTime, GregorianCalendar eventEndTime,
                 String location, String description) {
        mEventId = UUID.randomUUID();
        mTitle = title;
        mEventStartTime = eventStartTime;
        mEventEndTime = eventEndTime;
        mLocation = location;
        mDescription = description;
    }

    // Sorts Events in EventList by EventStartTime
    @Override
    public int compareTo(@NonNull Event otherEvent) {
        return getEventStartTime().compareTo(otherEvent.getEventStartTime());
    }

    @SuppressWarnings("all")
    public String getDateKey() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(mEventStartTime.getTime());
    }

    public UUID getEventId() {
        return mEventId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public GregorianCalendar getEventStartTime() {
        return (GregorianCalendar) mEventStartTime.clone();
    }

    public void setEventStartTime(GregorianCalendar eventStartTime) {
        mEventStartTime = eventStartTime;
    }

    public GregorianCalendar getEventEndTime() {
        return (GregorianCalendar) mEventEndTime.clone();
    }

    public void setEventEndTime(GregorianCalendar eventEndTime) {
        mEventEndTime = eventEndTime;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String toString() {
        return String.format("Event %s%nStart: %s%n  End: %s%nLocation: %s%nDescription: %s",
                getTitle(),
                mSdfParser.format(getEventStartTime().getTime()),
                mSdfParser.format(getEventEndTime().getTime()),
                getLocation(),
                getDescription());
    }
}
