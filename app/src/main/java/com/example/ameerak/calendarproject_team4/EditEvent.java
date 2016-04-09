package com.example.ameerak.calendarproject_team4;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EditEvent extends AppCompatActivity {

    private EditText mEventTitle;
    private Button mEventDate;
    private Button mEventStartTime;
    private Button mEventEndTime;
    private EditText mEventLocation;
    private EditText mEventDescription;

    // Formatting specifications (Ex. April 8, 2016)
    private final SimpleDateFormat mSdfDate = new SimpleDateFormat("MMMM d, yyyy");
    // Formatting specifications (Ex. 9:45 AM)
    private final SimpleDateFormat mSdfTime = new SimpleDateFormat("K:mm a");

    private GregorianCalendar mStartTime;
    private GregorianCalendar mEndTime;

    private Event mEvent;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        // Get reference to event (Should never be null)
        if (savedInstanceState != null) {
            mEvent = (Event) savedInstanceState
                    .getSerializable(getString(R.string.editEvent));
        }

        mEventTitle = (EditText) findViewById(R.id.event_title);
        mEventTitle.setText(mEvent.getTitle());

        mEventDate = (Button) findViewById(R.id.event_date);
        mEventDate.setText(mSdfDate.format(mEvent.getEventStartTime().getTime()));
        // DatePicker dialog for user to change event date
        mEventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                int year = gregorianCalendar.get(GregorianCalendar.YEAR);
                int month = gregorianCalendar.get(GregorianCalendar.MONTH);
                int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                                gregorianCalendar.set(year, monthOfYear, dayOfMonth);
                                mEventDate.setText(mSdfDate.format(gregorianCalendar.getTime()));
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

        mEventStartTime = (Button) findViewById(R.id.event_start_time);
        mEventStartTime.setText(mSdfTime.format(mEvent.getEventStartTime().getTime()));
        // TimePicker dialog for user to change event start time
        mEventStartTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = mEvent.getEventStartTime().get(GregorianCalendar.HOUR_OF_DAY);
                int minute = mEvent.getEventStartTime().get(GregorianCalendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(EditEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                                gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, selectedHour);
                                gregorianCalendar.set(GregorianCalendar.MINUTE, selectedMinute);
                                mEventStartTime.setText(mSdfTime.format(gregorianCalendar.getTime()));
                            }

                        }, hour, minute, false);
                timePicker.show();
            }
        });

        mEventEndTime = (Button) findViewById(R.id.event_end_time);
        mEventEndTime.setText(mSdfTime.format(mEvent.getEventEndTime().getTime()));
        // TimePicker dialog for user to change event end time
        mEventEndTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = mEvent.getEventStartTime().get(GregorianCalendar.HOUR_OF_DAY);
                int minute = mEvent.getEventStartTime().get(GregorianCalendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(EditEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, selectedHour);
                        gregorianCalendar.set(GregorianCalendar.MINUTE, selectedMinute);
                        mEventEndTime.setText(mSdfTime.format(gregorianCalendar.getTime()));
                    }

                }, hour, minute, false);
                timePicker.show();
            }
        });


        mEventLocation = (EditText) findViewById(R.id.event_location);
        mEventLocation.setText(mEvent.getLocation());

        mEventDescription = (EditText) findViewById(R.id.event_description);
        mEventDescription.setText(mEvent.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_event_menu, menu);
        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();

        switch (item.getItemId()) {

            // Send updated event back
            case R.id.menu_item_save_event:
                if (mEventTitle.getText().toString().length() == 0) {
                    mEvent.setTitle("(No Title)");
                } else {
                    mEvent.setTitle(mEventTitle.getText().toString());
                }
                // Times that are displayed are used to update event
                setTimes();
                mEvent.setEventStartTime(mStartTime);
                mEvent.setEventStartTime(mEndTime);
                mEvent.setLocation(mEventLocation.getText().toString());
                mEvent.setDescription(mEventDescription.getText().toString());

                intent.putExtra(getString(R.string.changedEvent), mEvent);
                setResult(RESULT_OK, intent);

                EditEvent.this.finish();
                return true;

            // Send unmodified event back
            case R.id.menu_item_cancel:
                intent.putExtra(getString(R.string.changedEvent), mEvent);
                setResult(RESULT_OK, intent);

                EditEvent.this.finish();
                return true;

            // Send event back to be deleted
            case R.id.menu_item_delete_event:
                intent.putExtra(getString(R.string.changedEvent), mEvent);
                setResult(RESULT_CANCELED, intent);

                EditEvent.this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTimes() {
        GregorianCalendar gregorianCalendar = parseDateField(mEventDate.getText().toString());

        gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY,
                parseTimeField(mEventStartTime.getText().toString()).get(GregorianCalendar.HOUR_OF_DAY));
        gregorianCalendar.set(GregorianCalendar.MINUTE,
                parseTimeField(mEventStartTime.getText().toString()).get(GregorianCalendar.MINUTE));
        mStartTime = gregorianCalendar;

        gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY,
                parseTimeField(mEventEndTime.getText().toString()).get(GregorianCalendar.HOUR_OF_DAY));
        gregorianCalendar.set(GregorianCalendar.MINUTE,
                parseTimeField(mEventEndTime.getText().toString()).get(GregorianCalendar.MINUTE));
        mEndTime = gregorianCalendar;
    }

    // Creating calendar from button text (Will eventually add a better way to get new date)
    public GregorianCalendar parseDateField(String date) {
        String[] parsedDate = date.split(" ");
        int month;
        switch (parsedDate[0].toUpperCase()) {
            case "JANUARY":
                month = 0;
                break;
            case "FEBRUARY":
                month = 1;
                break;
            case "MARCH":
                month = 2;
                break;
            case "APRIL":
                month = 3;
                break;
            case "MAY":
                month = 4;
                break;
            case "JUNE":
                month = 5;
                break;
            case "JULY":
                month = 6;
                break;
            case "AUGUST":
                month = 7;
                break;
            case "SEPTEMBER":
                month = 8;
                break;
            case "OCTOBER":
                month = 9;
                break;
            case "NOVEMBER":
                month = 10;
                break;
            case "DECEMBER":
                month = 11;
                break;
            default:
                month = -1;
        }
        int day = Integer.parseInt(parsedDate[1].replace(",", ""));
        int year = Integer.parseInt(parsedDate[2]);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, month, day);
        return gregorianCalendar;
    }

    public GregorianCalendar parseTimeField(String time) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String[] parsedTime = time.split(" ");

        String[] hourMin = parsedTime[0].split(":");
        int hours = Integer.parseInt(hourMin[0]);
        int miniutes = Integer.parseInt(hourMin[1]);

        String AM_PM = parsedTime[1];
        if (AM_PM.equalsIgnoreCase("PM")) {
            hours += 12;
        }

        gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, hours);
        gregorianCalendar.set(GregorianCalendar.MINUTE, miniutes);
        return gregorianCalendar;
    }
}
