package com.example.ameerak.calendarproject_team4;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;
import com.example.ameerak.calendarproject_team4.controller_layer.EventController;

import java.util.ArrayList;
import java.util.Collections;

public class ListEvents extends ListActivity {

    private final EventController CONTROLLER = EventController.get();

    private ArrayList<Event> mEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEventList = new ArrayList<>();

        //String title = getIntent().getSerializableExtra("Title");
        //mEventList = CONTROLLER.searchEvent(title);

        mEventList = CONTROLLER.searchEvent("(No Title)");
        Collections.sort(mEventList);

        EventListAdapter eventListAdapter = new EventListAdapter(this, R.layout.event_list_item, mEventList);
        setListAdapter(eventListAdapter);
    }
}
