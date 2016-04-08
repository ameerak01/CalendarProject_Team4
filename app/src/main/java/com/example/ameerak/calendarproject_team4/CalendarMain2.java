package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ameerak.calendarproject_team4.business_objects_layer.EventList;
import com.example.ameerak.calendarproject_team4.controller_layer.EventController;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarMain2 extends AppCompatActivity {
    EventController eventController;
    public GridView gridview;
    public TextAdapter textAdapter;
    public ImageView prevMonth, nextMonth;
    public GregorianCalendar calendar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendar = (GregorianCalendar) GregorianCalendar.getInstance();

        setContentView(R.layout.activity_calendar_main2);
        gridview = (GridView) findViewById(R.id.calendar_grid);
        textAdapter = new TextAdapter(this, calendar);
        gridview.setAdapter(textAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO: Once EventList is complete and EditEvent ad logic to call proper class

                addEvent(textAdapter.getDate(position));


            }
        });


        setMonth(calendar);

        prevMonth = (ImageView) findViewById(R.id.calendar_prev_button);
        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((calendar.get(GregorianCalendar.MONTH) + 1) > 0) {
                    calendar = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), (calendar.get(GregorianCalendar.MONTH) - 1), 1);
                } else {
                    calendar = new GregorianCalendar((calendar.get(GregorianCalendar.YEAR) - 1), 11, 1);
                }
                setMonth(calendar);
                //Log.d("Calender_Prev_On_Click", "Just after Set Month");
            }
        });



       nextMonth = (ImageView) findViewById(R.id.calendar_next_button);
        nextMonth.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( (calendar.get(GregorianCalendar.MONTH) + 1) < 13) {
                    calendar = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), (calendar.get(GregorianCalendar.MONTH) + 1), 1);
                }
                else {
                    calendar = new GregorianCalendar((calendar.get(calendar.YEAR) + 1), 1, 1);
                }
                setMonth(calendar);
                //Log.d("Calender_Next_On_Click", "Just after Set Month");
            }
        });






        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setMonth(GregorianCalendar calendar) {
        this.calendar = calendar;
        int month = (calendar.get(GregorianCalendar.MONTH) + 1);
        String monthString;
        TextView textView = (TextView) findViewById(R.id.calendar_date_display);
        switch (month) {
            case 1: monthString = "January";
                break;
            case 2: monthString = "February";
                break;
            case 3: monthString = "March";
                break;
            case 4: monthString = "April";
                break;
            case 5: monthString = "May";
                break;
            case 6: monthString = "June";
                break;
            case 7: monthString = "July";
                break;
            case 8: monthString = "August";
                break;
            case 9: monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "ERROR";
                break;
        }


        textView.setText(monthString + " " + calendar.get(GregorianCalendar.YEAR));



        textAdapter.changeCalendar(calendar);
        //textAdapter.notifyDataSetChanged(calendar);
        //textAdapter.notifyDataSetChanged();
        //gridview.setAdapter(textAdapter);
        //invalidateViews();

        Log.d("Set_Month", monthString + " " + calendar.get(GregorianCalendar.YEAR));

    }

    @Override
    public void onResume() {
        super.onResume();



    }




    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CalendarMain2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ameerak.calendarproject_team4/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CalendarMain2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ameerak.calendarproject_team4/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    // Call add event to create an event
    // To retrieve object in second Activity
    // use getIntent().getSerializableExtra("com.example.ameerak.calendarproject_team4.addCalendar");
    public void addEvent(GregorianCalendar calendar)
    {
        Intent intent = new Intent(this, AddEvent.class);
        intent.putExtra("com.example.ameerak.calendarproject_team4.addCalendar", calendar);
        startActivity(intent);
    }

    // Call add event to create an event
    // To retrieve object in second Activity
    // use getIntent().getSerializableExtra("com.example.ameerak.calendarproject_team4.editEvent");
    /*
    public void editEvent(GregorianCalendar calendar)
    {
        Intent intent = new Intent(this, EditEvent.class);
        intent.putExtra("com.example.ameerak.calendarproject_team4.editEvent",calendar);
        startActivity(intent);
    }
    */



}



