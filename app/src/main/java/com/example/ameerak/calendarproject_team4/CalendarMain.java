package com.example.ameerak.calendarproject_team4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;
import com.example.ameerak.calendarproject_team4.controller_layer.EventController;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarMain extends AppCompatActivity {
    EventController eventController = EventController.get();
    public GridView gridview;
    public TextAdapter textAdapter;
    public ImageView prevMonth, nextMonth;
    public GregorianCalendar calendar;

    static final int ADD_EVENT = 0;
    static final int EDIT_EVENT = 1;
    static final int PICK_EVENT = 2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendar = (GregorianCalendar) GregorianCalendar.getInstance();

        setContentView(R.layout.activity_calendar_main);
        gridview = (GridView) findViewById(R.id.calendar_grid);
        textAdapter = new TextAdapter(this, calendar, eventController);
        gridview.setAdapter(textAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList eventList = eventController.getEvents(textAdapter.getDate(position));

                if(eventList == null) {
                    addEvent(textAdapter.getDate(position));
                }
                //Event list may return an empty array for days that had events deleted
                else if(eventList.size() == 0) {
                    addEvent(textAdapter.getDate(position));
                }
                else if(eventList.size() > 1) {
                    pickEvent(eventList);
                }
                else {
                    editEvent((Event) eventList.get(0));
                }


            }
        });


        setMonth(calendar);

        prevMonth = (ImageView) findViewById(R.id.calendar_prev_button);
        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((calendar.get(GregorianCalendar.MONTH) + 1) > 0) {
                    calendar = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), (calendar.get(GregorianCalendar.MONTH) - 1), calendar.get(GregorianCalendar.DAY_OF_MONTH));
                } else {
                    calendar = new GregorianCalendar((calendar.get(GregorianCalendar.YEAR) - 1), 11, calendar.get(GregorianCalendar.DAY_OF_MONTH));
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
                    calendar = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), (calendar.get(GregorianCalendar.MONTH) + 1), calendar.get(GregorianCalendar.DAY_OF_MONTH));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        // Check which request we're responding to
        if (requestCode == ADD_EVENT) {
            // If result not ok do nothing
            if(resultCode == RESULT_OK) {
                Event newEvent = (Event) resultIntent.getSerializableExtra(getString(R.string.newEvent));
                eventController.addEvent(newEvent);
            }

        }
        else if (requestCode == EDIT_EVENT) {
            // If the code was not RESULT_OK do nothing
            if(resultCode == RESULT_OK) {
                Event changedEvent = (Event) resultIntent.getSerializableExtra(getString(R.string.changedEvent));
                eventController.updateEvent(changedEvent);
            }
            // Else it was a delete code
            else if (resultCode == RESULT_CANCELED){
                if (resultIntent == null) {
                    // Do nothing if user clicked back on editEvent
                } else {
                    Event changedEvent = (Event) resultIntent.getSerializableExtra(getString(R.string.changedEvent));
                    eventController.deleteEvent(changedEvent.getEventId());
                }
            }
        }
        else {

        }
        textAdapter.changeCalendar(calendar);
    }




    /**
     * Call add event to create an event. To retrieve object in addEvent use the following
     * GregorianCalendar calendar = (GregorianCalendar) getIntent().getSerializableExtra(R.string.addEvent));
     *
     * !!!!!!!!!!!!!!!!!!!!!!! To Return the new event !!!!!!!!!!!!!!!!!!!!!!!
     * Intent intent = new Intent();
     * intent.putExtra(getString(R.string.newEvent), ***The new event you made***);
     * setResult(RESULT_OK,intent);
     *
     * @param calendar
     */
    public void addEvent(GregorianCalendar calendar) {
        Calendar now = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
        Intent intent = new Intent(this, AddEvent.class);
        intent.putExtra(getString(R.string.addEvent), calendar);
        startActivityForResult(intent, ADD_EVENT);
    }

    /**
     * Call editEvent to edit an event. To retrieve object in EditEvent use the following
     * Event event = (Event) getIntent().getSerializableExtra(R.string.editEvent));
     *
     * !!!!!!!!!!!!!!!!!!!!!!! To Return the edited event !!!!!!!!!!!!!!!!!!!!!!!
     * Intent intent = new Intent();
     * intent.putExtra(getString(R.string.changedEvent), ***The event you edited***);
     * setResult(RESULT_OK,intent);
     *
     *
     * @param eventToBeEdited
     */
    public void editEvent(Event eventToBeEdited)
    {
        Intent intent = new Intent(this, EditEvent.class);
        intent.putExtra(getString(R.string.editEvent),eventToBeEdited);
        startActivityForResult(intent, EDIT_EVENT);
    }

    /**
     * Call this to start the event picker. To retrieve object in EventPicker use the following
     *
     * CANT PASS A LINKED LIST VIA PUTEXTRA. WILL NEED TO FIND ANOTHER WAY!
     *
     * !!!!!!!!!!!!!!!!!!!!!!! To Return the edited event !!!!!!!!!!!!!!!!!!!!!!!
     * Intent intent = new Intent();
     * intent.putExtra(getString(R.string.changedEvent), ***The event you edited***);
     * setResult(RESULT_OK,intent);
     *
     * @param eventList
     */
    public void pickEvent(ArrayList eventList)
    {
        //Intent intent = new Intent(this, SelectEvent.class);
        //intent.putExtra(getString(R.string.pickEvent), eventList);
        //startActivityForResult(intent, PICK_EVENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {

            // Send updated event back
            case R.id.menu_item_add_event:

                // do stuff
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}



