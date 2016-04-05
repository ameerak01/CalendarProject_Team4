package com.example.ameerak.calendarproject_team4.business_objects_layer;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Event implements Serializable, Comparable<Event> {

    private UUID mEventId;
    private String mTitle;
    private GregorianCalendar mEventStartTime;
    private GregorianCalendar mOutdatedStartTime; // For updating purposes within EventList
    private GregorianCalendar mEventEndTime;
    private String mLocation;
    private String mDescription;

    public Event(String title, GregorianCalendar calendar, GregorianCalendar eventEndTime,
                 String location, String description) {
        mEventId = UUID.randomUUID();
        mTitle = title;
        mEventStartTime = calendar;
        mEventEndTime = eventEndTime;
        mLocation = location;
        mDescription = description;
        mOutdatedStartTime = calendar;
    }

    @Override
    public int compareTo(@NonNull Event otherEvent) {
        return getEventStartTime().compareTo(otherEvent.getEventStartTime());
    }

    public String getDateKey() {
        return createDateKey(mEventStartTime);
    }

    public String getOldDateKey() {
        return createDateKey(mOutdatedStartTime);
    }

    @SuppressWarnings("all")
    private String createDateKey(GregorianCalendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(calendar.getTime());
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
        return mEventStartTime;
    }

    public void setEventStartTime(GregorianCalendar eventStartTime) {
        mOutdatedStartTime = mEventStartTime;
        mEventStartTime = eventStartTime;
    }

    public GregorianCalendar getEventEndTime() {
        return mEventEndTime;
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
}
