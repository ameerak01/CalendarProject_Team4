package com.example.ameerak.calendarproject_team4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddEvent_New extends AppCompatActivity {

    private EditText mEventTitle;
    private Button mEventDate;
    private Button mEventStartTime;
    private Button mEventEndTime;
    private EditText mEventLocation;
    private EditText mEventDescription;

    // Formatting specifications (Ex. April 8, 2016)
    private final SimpleDateFormat mSdfDate = new SimpleDateFormat("MMMM d, yyyy");
    // Formatting specifications (Ex. 9:45 AM)
    private final SimpleDateFormat mSdfTime = new SimpleDateFormat("h:mm a");
    // Text parser (April 9, 2016 9:45 AM) --> Date
    private final SimpleDateFormat mSdfParser = new SimpleDateFormat("MMMM d, yyyy h:mm a");

    private GregorianCalendar mStartTime;
    private GregorianCalendar mEndTime;

    private Event mEvent;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        mEvent = new Event("", (GregorianCalendar) GregorianCalendar.getInstance(),(GregorianCalendar) GregorianCalendar.getInstance(),"", "");

        mStartTime = (GregorianCalendar) getIntent().getSerializableExtra(getString(R.string.addEvent));
        mEvent.setEventStartTime(mStartTime);

        mEndTime = (GregorianCalendar) mStartTime.clone();
        mEvent.setEventEndTime(mEndTime);

        mEventTitle = (EditText) findViewById(R.id.event_title);
        mEventTitle.setText(mEvent.getTitle());

        mEventDate = (Button) findViewById(R.id.event_date);
        mEventDate.setText(mSdfDate.format(mStartTime.getTime()));
        // DatePicker dialog for user to change event date
        mEventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int year = mStartTime.get(Calendar.YEAR);
                int month = mStartTime.get(Calendar.MONTH);
                int day = mStartTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEvent_New.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                mEventDate.setText(mSdfDate.format(calendar.getTime()));
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        mEventStartTime = (Button) findViewById(R.id.event_start_time);
        mEventStartTime.setText(mSdfTime.format(mStartTime.getTime()));
        // TimePicker dialog for user to change event start time
        mEventStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = mStartTime.get(Calendar.HOUR_OF_DAY);
                int minute = mStartTime.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(AddEvent_New.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                                calendar.set(Calendar.MINUTE, selectedMinute);
                                mEventStartTime.setText(mSdfTime.format(calendar.getTime()));
                            }
                        }, hour, minute, false);

                timePicker.show();
            }
        });

        mEventEndTime = (Button) findViewById(R.id.event_end_time);
        mEventEndTime.setText(mSdfTime.format(mEndTime.getTime()));
        // TimePicker dialog for user to change event end time
        mEventEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = mEvent.getEventEndTime().get(Calendar.HOUR_OF_DAY);
                int minute = mEvent.getEventEndTime().get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(AddEvent_New.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                                calendar.set(Calendar.MINUTE, selectedMinute);
                                mEventEndTime.setText(mSdfTime.format(calendar.getTime()));
                            }
                        }, hour, minute, false);
                timePicker.show();
            }
        });


        mEventLocation = (EditText) findViewById(R.id.event_location);
        mEventLocation.setText(mEvent.getLocation());

        mEventDescription = (EditText) findViewById(R.id.event_description);
        mEventDescription.setText(mEvent.getDescription());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_event_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {

            // Send updated event back
            case R.id.menu_item_add_event:

                // If event title field empty then default value set
                if (mEventTitle.getText().toString().length() == 0) {
                    mEvent.setTitle("(No Title)");
                } else {
                    mEvent.setTitle(mEventTitle.getText().toString());
                }

                // Times that are displayed are used to update event
                try {
                    mStartTime.setTime(mSdfParser.parse(mEventDate + " " + mEventStartTime));
                    mEndTime.setTime(mSdfParser.parse(mEventDate + " " + mEventEndTime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mEvent.setEventStartTime(mStartTime);
                mEvent.setEventStartTime(mEndTime);

                mEvent.setLocation(mEventLocation.getText().toString());
                mEvent.setDescription(mEventDescription.getText().toString());

                intent.putExtra(getString(R.string.newEvent), mEvent);
                setResult(RESULT_OK, intent);

                AddEvent_New.this.finish();
                return true;

            // Send unmodified event back
            case R.id.menu_item_cancel:
                intent.putExtra(getString(R.string.newEvent), mEvent);
                setResult(RESULT_CANCELED, intent);

                AddEvent_New.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "AddEvent_New Page", // TODO: Define a title for the content shown.
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
                "AddEvent_New Page", // TODO: Define a title for the content shown.
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
}
