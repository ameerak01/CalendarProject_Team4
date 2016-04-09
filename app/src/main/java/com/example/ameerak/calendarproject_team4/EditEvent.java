package com.example.ameerak.calendarproject_team4;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EditEvent extends AppCompatActivity {

    private final String getEventKey =
            "com.example.ameerak.calendarproject_team4.EditEvent";

    private EditText mEventTitle;
    private Button mEventDate;
    private Button mEventStartTime;
    private Button mEventEndTime;
    private EditText mEventLocation;
    private EditText mEventDescription;
    private Button mSaveButton;
    private Button mCancelButton;

    private GregorianCalendar mStartTime;
    private GregorianCalendar mEndTime;

    // Formatting specifications (Ex. April 8, 2016)
    private final SimpleDateFormat mSdfDate = new SimpleDateFormat("MMMM dd, yyyy");
    // Formatting specifications (Ex. 09:45 AM)
    private final SimpleDateFormat mSdfTime = new SimpleDateFormat("KK:mm a");

    private Event mEvent;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);


        // Get reference to event (Should never be null)
        if (savedInstanceState != null) {
            mEvent = (Event) savedInstanceState.getSerializable(getEventKey);
        }

        mStartTime = mEvent.getEventStartTime();
        mEndTime = mEvent.getEventEndTime();

        mEventTitle = (EditText) findViewById(R.id.event_title);
        mEventTitle.setText(mEvent.getTitle());

        mEventDate = (Button) findViewById(R.id.event_date);
        mEventDate.setText(mSdfDate.format(mEvent.getEventStartTime().getTime()));
        // DatePicker dialog for user to change event date
        mEventDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int year = mStartTime.get(GregorianCalendar.YEAR);
                int month = mStartTime.get(GregorianCalendar.MONTH);
                int day = mStartTime.get(GregorianCalendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mStartTime.set(year, monthOfYear, dayOfMonth);
                                mEndTime.set(year, monthOfYear, dayOfMonth);
                                mEventDate.setText(mSdfDate.format(mStartTime));

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

                int hour = mStartTime.get(GregorianCalendar.HOUR_OF_DAY);
                int minute = mStartTime.get(GregorianCalendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(EditEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                mStartTime.set(GregorianCalendar.HOUR_OF_DAY, selectedHour);
                                mStartTime.set(GregorianCalendar.MINUTE, selectedMinute);

                                mEventStartTime.setText(mSdfTime.format(mStartTime));
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

                int hour = mEndTime.get(GregorianCalendar.HOUR_OF_DAY);
                int minute = mEndTime.get(GregorianCalendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(EditEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mEndTime.set(GregorianCalendar.HOUR_OF_DAY, selectedHour);
                        mEndTime.set(GregorianCalendar.MINUTE, selectedMinute);

                        mEventEndTime.setText(mSdfTime.format(mEndTime));
                    }

                }, hour, minute, false);
                timePicker.show();
            }
        });


        mEventLocation = (EditText) findViewById(R.id.event_location);
        mEventLocation.setText(mEvent.getDescription());

        mEventDescription = (EditText) findViewById(R.id.event_description);
        mEventDescription.setText(mEvent.getDescription());

        // Updated event is created just need a form to send it back
        mSaveButton = (Button) findViewById(R.id.save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEventTitle.getText().toString().length() == 0) {
                    mEvent.setTitle("No Title");
                } else {
                    mEvent.setTitle(mEventTitle.getText().toString());
                }
                mEvent.setEventStartTime(mStartTime);
                mEvent.setEventStartTime(mEndTime);
                mEvent.setLocation(mEventLocation.getText().toString());
                mEvent.setDescription(mEventDescription.getText().toString());
            }
        });

        // Returning unmodifed event back
        mCancelButton = (Button) findViewById(R.id.cancel);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
