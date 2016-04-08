package com.example.ameerak.calendarproject_team4.business_objects_layer;


import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.UUID;

public class EventList {

    private static EventList sEventList;

    private Hashtable<String, LinkedList<Event>> mEvents;

    // Only want a single event list in system
    public static EventList get() {
        if (sEventList == null) {
            sEventList = new EventList();
        }
        return sEventList;
    }

    private EventList() {
        mEvents = new Hashtable<>();
    }

    public LinkedList<Event> getEvents(String dateKey) {
        return mEvents.get(dateKey);
    }

    public void addEvent(Event event) {
        LinkedList<Event> eventList = getEvents(event.getDateKey());

        if (eventList == null) {
            eventList = new LinkedList<>();
            eventList.add(event);
        } else {
            eventList.add(event);
        }

        Collections.sort(eventList);
        mEvents.put(event.getDateKey(), eventList);
    }

    public void updateEvent(Event event) {
        deleteEvent(event.getEventId());
        addEvent(event);
    }

    public Event getEvent(UUID eventId) {

        for (LinkedList<Event> eventList : mEvents.values()){
            for (Event event : eventList) {
                if (event.getEventId().equals(eventId)) {
                    return event;
                }
            }
        }
        return null;
    }

    public void deleteEvent(UUID eventId) {

        for (LinkedList<Event> eventList : mEvents.values()) {
            for (Event event : eventList) {
                if (event.getEventId().equals(eventId)) {
                    eventList.remove(event);
                    break;
                }
            }
        }
    }

    @SuppressWarnings("all")
    public String createDateKey(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(calendar.getTime());
    }

    @SuppressWarnings("all")
    public String createDateKey(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        return simpleDateFormat.format(calendar.getTime());
    }
}
