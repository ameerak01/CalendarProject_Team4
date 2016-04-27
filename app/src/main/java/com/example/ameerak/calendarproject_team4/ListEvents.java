package com.example.ameerak.calendarproject_team4;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
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

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mEventList = CONTROLLER.searchEvent(query);
        }
        else {
            mEventList = CONTROLLER.searchEvent("(No Title)");
        }
        Collections.sort(mEventList);

        EventListAdapter eventListAdapter = new EventListAdapter(this, R.layout.event_list_item, mEventList);
        setListAdapter(eventListAdapter);
    }
}
