package com.example.ameerak.calendarproject_team4.controller_layer;

import android.content.Context;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;
import com.example.ameerak.calendarproject_team4.business_objects_layer.EventList;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.UUID;

public class EventController {

    private static EventController sEventController;

    private EventList mEventList;

    public static EventController get(Context context) {
        if (sEventController == null) {
            sEventController = new EventController(context);
        }
        return sEventController;
    }

    private EventController(Context context) {
        mEventList = EventList.get();
    }

    // Months go from 0-11
    public LinkedList<Event> getEvents(int year, int month, int day) {
        return mEventList.getEvents(mEventList.createDateKey(year, month, day));
    }

    // Get list of events for specific date or null if no events for that date exist
    public LinkedList<Event> getEvents(GregorianCalendar gregorianCalendar) {
        final int GET_YEAR = GregorianCalendar.YEAR;
        final int GET_MONTH = GregorianCalendar.MONTH;
        final int GET_DAY = GregorianCalendar.DAY_OF_MONTH;

        return mEventList.getEvents(mEventList.createDateKey(gregorianCalendar.get(GET_YEAR),
                gregorianCalendar.get(GET_MONTH), gregorianCalendar.get(GET_DAY)));
    }

    // Called after AddEvent sends event back to Main
    public void addEvent(Event event) {
        mEventList.addEvent(event);
    }

    // Called after EditEvent sends event back to Main
    public void updateEvent(Event event) {
        mEventList.updateEvent(event);
    }

    public Event getEvent(UUID eventId) {
        return mEventList.getEvent(eventId);
    }

    // Called after DeleteEvent sends eventId back to Main
    public void deleteEvent(UUID eventId) { 
        mEventList.deleteEvent(eventId);
    }
}
