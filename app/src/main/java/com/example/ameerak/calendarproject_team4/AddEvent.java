package com.example.ameerak.calendarproject_team4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("dd-MM-yyyy");
        DatePicker datepick = (DatePicker) this.findViewById(R.id.datePicker_main);;
        String dateString= ss.format(datepick);
        TextView editText = (TextView) this.findViewById(R.id.date_editText);
        // To set the text as the date like MM/DD/YEAR
        editText.setText(datepick.getMonth() + "/" + datepick.getDayOfMonth() + "/" + datepick.getYear());
       // editText.setText("Hello");

        // To set the text as the time
       // editText.setText(datepick.getHours() + ":" + datepick.getMinutes());
    }
    public void goToShareView(View v)
    {
        startActivity(new Intent(this, ShareEvent.class));
    }


}
