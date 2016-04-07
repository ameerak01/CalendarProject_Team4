package com.example.ameerak.calendarproject_team4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EditEvent extends AppCompatActivity
{

    private EditText eventTitle;
    private EditText eventDate;
    private EditText eventTime;
    private EditText eventLocation;
    private EditText eventDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        Event event = new Event("TITLE", new GregorianCalendar(), new GregorianCalendar(), "Texas", "Description");

        eventTitle = (EditText) findViewById(R.id.event_editText) ;
        eventTitle.setText(event.getTitle());

        // Better use a selector or dialog box for editing the date
        eventDate = (EditText) findViewById(R.id.date_editText);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("LL dd yyyy");
        eventDate.setText(simpleDateFormat.format(event.getEventStartTime().getTime()));

        eventDate.setText(event.getEventStartTime().toString());

        eventLocation = (EditText) findViewById(R.id.location_editText);
        eventTitle.setText(event.getLocation());

        eventDescription = (EditText) findViewById(R.id.description_editText);
        eventDescription.setText(event.getDescription());



    }
}
