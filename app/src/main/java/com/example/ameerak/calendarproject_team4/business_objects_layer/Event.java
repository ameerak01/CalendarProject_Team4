package com.example.ameerak.calendarproject_team4.business_objects_layer;


import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Event implements Comparable<Event> {

    private UUID mEventId;
    private String mTitle;
    private GregorianCalendar mCalendar;
    private GregorianCalendar mOutdatedCalendar; // Used for updating purposes within EventList
    private String mLocation;
    private String mDescription;

    public Event(String title, GregorianCalendar calendar, String location, String description) {
        mEventId = UUID.randomUUID();
        mTitle = title;
        mCalendar = calendar;
        mLocation = location;
        mDescription = description;
        mOutdatedCalendar = calendar;
    }

    @Override
    public int compareTo(@NonNull Event otherEvent) {
        return getCalendar().compareTo(otherEvent.getCalendar());
    }

    public UUID getEventId() {
        return mEventId;
    }

    @SuppressWarnings("all")
    public String getDateKey() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(getCalendar().getTime());
    }

    @SuppressWarnings("all")
    public String getOldDateKey() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(getOutdatedCalendar().getTime());
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public GregorianCalendar getCalendar() {
        return mCalendar;
    }

    public void setCalendar(GregorianCalendar calendar) {
        mOutdatedCalendar = mCalendar;
        mCalendar = calendar;
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

    public GregorianCalendar getOutdatedCalendar() {
        return mOutdatedCalendar;
    }
}
