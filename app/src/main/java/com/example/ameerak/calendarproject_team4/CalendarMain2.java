package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.GregorianCalendar;

public class CalendarMain2 extends AppCompatActivity {
    //public final static String EXTRA_MESSAGE = "com.example.ameerak.calendarproject_team4.MESSAGE";
    CalendarView cView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_main2);
        cView = new CalendarView(this);
        cView.updateCalendar();
        //Intent intent = new Intent(this, CalendarView.class);
        //intent.putExtra(EXTRA_MESSAGE, this);
        //startActivity(intent);

    }


    public class CalendarView extends LinearLayout {
        // internal components
        private LinearLayout header;
        private ImageView btnPrev;
        private ImageView btnNext;
        private TextView txtDate;
        private GridView grid;
        //private Calendar calendar;
        private GregorianCalendar currentDate;
        private LayoutInflater inflater;

        public CalendarView(Context context) {
            super(context);
            initControl(context);
            //calendar = GregorianCalendar.getInstance();
            currentDate = new GregorianCalendar();
            updateCalendar();

        }

        protected class CalendarAdapter {

            public void updateData(ArrayList<Date> cells) {

            }
        }

        /**
         * Load component XML layout
         */
        private void initControl(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
            GregorianCalendar calendar = (GregorianCalendar) currentDate.clone();
            int daysCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            // determine the cell for current month's beginning
            calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
            int monthBeginningCell = calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;

            // move calendar backwards to the beginning of the week
            calendar.add(GregorianCalendar.DAY_OF_MONTH, -monthBeginningCell);

            // fill cells (42 days calendar as per our business logic)
            while (cells.size() < daysCount) {
                cells.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            // update grid
            //((CalendarAdapter) grid.getAdapter()).updateData(cells);

            // update title
            SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
            txtDate.setText(sdf.format(currentDate.getTime()));
        }

        //@Override
        public View getView(int position, View view, ViewGroup parent)
        {
            // day in question
            //Date date = getItem(position);

            // today
            //Date today = new Date();

            // inflate item if it does not exist yet
            if (view == null)
                //view = inflater.inflate(R.layout.control_calendar_day, parent, false);
                view = inflater.inflate(position,parent);

            // if this day has an event, specify event image
            //view.setBackgroundResource(eventDays.contains(date)) ?
            //        R.drawable.reminder : 0);

            // clear styling
            //view.setTypeface(null, Typeface.NORMAL);
            //view.setTextColor(Color.BLACK);

            //if (date.getMonth() != today.getMonth() ||
            //        date.getYear() != today.getYear())
            //{
            // if this day is outside current month, grey it out
            //    view.setTextColor(getResources().getColor(R.color.greyed_out));
            // }
            //else if (date.equals(today))
            //{
            //    // if it is today, set it to blue/bold
            //    view.setTypeface(null, Typeface.BOLD);
            //    view.setTextColor(getResources().getColor(R.color.today));
            //}

            // set text
            TextView text = (TextView) view;
            //view.setText(String.valueOf(date.getDate()));
            text.setText(String.valueOf(position));
            return view;
        }
    }




}



