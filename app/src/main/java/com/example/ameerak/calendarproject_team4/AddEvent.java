package com.example.ameerak.calendarproject_team4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        GregorianCalendar c = new GregorianCalendar();
        GregorianCalendar endtime = new GregorianCalendar();
        //SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
        //DatePicker datepick = (DatePicker) this.findViewById(R.id.datePicker_main);
        //String dateString= ss.format(datepick);
        TextView date_text = (TextView) this.findViewById(R.id.date_editText);
        TextView event_text = (TextView) this.findViewById(R.id.event_editText);
        TextView location_text = (TextView) this.findViewById(R.id.location_editText);
        TextView description_text = (TextView) this.findViewById(R.id.description_editText);

        // To set the text as the date like MM/DD/YEAR
        date_text.setText(c.get(Calendar.MONTH) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR));
       // editText.setText("Hello");

        // To set the text as the time
       // editText.setText(datepick.getHours() + ":" + datepick.getMinutes());

        Event event = new Event(event_text.toString(), c,endtime, location_text.toString(), description_text.toString());

        getIntent().getSerializableExtra("com.example.ameerak.calendarproject_team4.AddEvent");

    }

    public void goToShareView(View v)
    {
        startActivity(new Intent(this, ShareEvent.class));
    }

}
