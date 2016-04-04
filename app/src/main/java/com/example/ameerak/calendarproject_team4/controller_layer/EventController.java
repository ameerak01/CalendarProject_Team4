package com.example.ameerak.calendarproject_team4.controller_layer;

import android.content.Context;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;
import com.example.ameerak.calendarproject_team4.business_objects_layer.EventList;

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

    // Returns a LinkedList of events pertaining to specified date or null if no events for specified date exist
    // months go from 0-11, so if you wanted list for events on April 4, 2016 you call: eventController.getEvents(2016, 3, 4);
    public LinkedList<Event> getEvents(int year, int month, int day) {
        return mEventList.getEvents(mEventList.createDateKey(year, month, day));
    }

    public void addEvent(Event event) {
        mEventList.addEvent(event);
    }

    public Event getEvent(UUID eventId) {
        return mEventList.getEvent(eventId);
    }

    public void updateEvent(Event event) {
        mEventList.updateEvent(event);
    }

    public void deleteEvent(UUID eventId) {
        mEventList.removeEvent(eventId);
    }

}
