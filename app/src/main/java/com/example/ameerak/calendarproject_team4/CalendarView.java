/*
* Based on this tutorial by AHMED AL-AMIR
* https://www.toptal.com/android/android-customization-how-to-build-a-ui-component-that-does-what-you-want
*
*
*
* */


package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarView extends LinearLayout {
    // internal components
    private LinearLayout header;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView grid;
    private Calendar calendar;
    private Date currentDate;

    public CalendarView(Context context) {
        super(context);
        initControl(context);
        calendar = Calendar.getInstance();
        currentDate = calendar.getTime();
    }

    protected class CalendarAdapter {

        public void updateData(ArrayList<Date> cells) {

        }
    }

    /**
     * Load component XML layout
     */
    private void initControl(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.activity_calendar_main2, this);

        // layout is inflated, assign local variables to components
        header = (LinearLayout) findViewById(R.id.calendar_header);
        btnPrev = (ImageView) findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView) findViewById(R.id.calendar_next_button);
        txtDate = (TextView) findViewById(R.id.calendar_date_display);
        grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void updateCalendar() {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();
        int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        // fill cells (42 days calendar as per our business logic)
        while (cells.size() < daysCount) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // update grid
        ((CalendarAdapter) grid.getAdapter()).updateData(cells);

        // update title
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        txtDate.setText(sdf.format(currentDate.getTime()));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        // day in question
        Date date = getItem(position);

        // today
        Date today = new Date();

        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.control_calendar_day, parent, false);

        // if this day has an event, specify event image
        view.setBackgroundResource(eventDays.contains(date)) ?
                R.drawable.reminder : 0);

        // clear styling
        view.setTypeface(null, Typeface.NORMAL);
        view.setTextColor(Color.BLACK);

        if (date.getMonth() != today.getMonth() ||
                date.getYear() != today.getYear())
        {
            // if this day is outside current month, grey it out
            view.setTextColor(getResources().getColor(R.color.greyed_out));
        }
        else if (date.equals(today))
        {
            // if it is today, set it to blue/bold
            view.setTypeface(null, Typeface.BOLD);
            view.setTextColor(getResources().getColor(R.color.today));
        }

        // set text
        view.setText(String.valueOf(date.getDate()));

        return view;
    }
}
