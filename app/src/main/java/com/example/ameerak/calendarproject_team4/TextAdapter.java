package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by Neil Simmons on 4/6/16.
 *
 * This adapter is to display a 7 column, 6 row, 42 cell grid of dates.
 * It mathematically determines where on the grid the current month should
 * start so that it starts in the first row.
 */
public class TextAdapter extends BaseAdapter {

    private Context context;
    private GregorianCalendar calendar;
    private GregorianCalendar prevMonthCalendar;
    private int dayOfWeek, dayOfMonth, daysInMonth,
            firstDayOfMonth, prevMonth, lengthPreviousMonth,
            prevDaysDisplayed;

    public TextAdapter(Context context, GregorianCalendar calendar) {
        this.context = context;
        this.calendar = calendar;
        setParams(calendar);


    }

    private void setParams(GregorianCalendar calendar) {
        this.dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        this.dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        this.daysInMonth = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        this.prevMonth = calendar.get(GregorianCalendar.MONTH) - 1;
        // TODO: Make this not break for month = jan
        this.prevMonthCalendar = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), prevMonth,1);
        this.lengthPreviousMonth = this.prevMonthCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        String stringToPrint;


        /****************************
         * Algorithm to find the first day of the month, with respect to the grid layout.
         * If the day of the week and month are equal then the month started on the first
         * saturday represented in the grid, which is 1.
         *
         * If the day of the month is less than the day of the week then the first day of the
         * month falls within the first week, and the month started on the grid day represented
         * by day of the week - day of the month.
         *
         * The else mathematically takes the current day of the month back to the last saturday, via
         * the same method as the previous equation, then gets the modulus of the remaining days and 7.
         * */
        if(this.dayOfMonth == this.dayOfWeek) {
            this.firstDayOfMonth = 0;
        }
        else if (this.dayOfMonth < this.dayOfWeek) {
            this.firstDayOfMonth = this.dayOfWeek - this.dayOfMonth;
        }
        else {
            // Not tested as of 4/8 - NS
            this.firstDayOfMonth = (7-this.dayOfWeek)%7;
        }

        // Finds the first day of the previous month that will show in the first grid position
        this.prevDaysDisplayed = this.lengthPreviousMonth - this.firstDayOfMonth;

        stringToPrint = "dayOfWeek: " + Integer.toString(dayOfWeek) + ", dayOfMonth: " + Integer.toString(dayOfMonth) + ", daysInMonth: " + Integer.toString(daysInMonth) + ", prevMonth: " + Integer.toString(prevMonth) + ", lengthPreviousMonth: " + Integer.toString(lengthPreviousMonth) + ", firstDayOfMonth: " + Integer.toString(firstDayOfMonth);
        Log.d("Set_Params_Output", stringToPrint);
    }

    public void notifyDataSetChanged(GregorianCalendar calendar) {
        this.calendar = null;
        this.calendar = calendar;
        this.setParams(calendar);
        super.notifyDataSetChanged();

    }

    public int getCount() {
        return 42;
    }

    public Object getItem(int position) {
        return null;
    }

    /****
     * @param position
     * @return GregorianCalendar based on position in array given
     */

    public GregorianCalendar getDate(int position) {
        int workingPosition = position, today;

        // Grid position provided is in the previous month
        if(workingPosition <= firstDayOfMonth ) {
            today = prevDaysDisplayed + workingPosition;

            // Check that the previous month was not december of the previous year
            if (this.calendar.get(GregorianCalendar.MONTH) - 1 >= 0) {
                return new GregorianCalendar(this.calendar.get(GregorianCalendar.YEAR), this.calendar.get(GregorianCalendar.MONTH) - 1, today);
            }
            else {
                return new GregorianCalendar(this.calendar.get(GregorianCalendar.YEAR) - 1, 11, today);
            }

        }
        // Indicates the current position of the grid is the current month
        else if (workingPosition <= (daysInMonth + firstDayOfMonth)) {
            today = workingPosition - firstDayOfMonth;
            return new GregorianCalendar(this.calendar.get(GregorianCalendar.YEAR), this.calendar.get(GregorianCalendar.MONTH), today);
        }
        // Otherwise the current position is outside of current month.
        else {
            today = workingPosition - firstDayOfMonth - daysInMonth;
            // Check that the next month is not January of the next year
            if(this.calendar.get(GregorianCalendar.MONTH) + 1 < 12) {
                return new GregorianCalendar(this.calendar.get(GregorianCalendar.YEAR), this.calendar.get(GregorianCalendar.MONTH) + 1, today);
            }
            else {
                return new GregorianCalendar(this.calendar.get(GregorianCalendar.YEAR) + 1, 0, today);
            }
        }
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        /**
        * Today is number that will end up printed in the grid.
        * Working index is position plus 1, as the grid is 0
        * indexed. This was done to make dealing with dates
        * more readable as they are not 0 indexed.
        */
        int today, workingIndex = position + 1;

        // Auto generated stuff
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(85, 85));
        }
        else {
            tv = (TextView) convertView;
        }

        //Center the dates in the grid
        tv.setGravity(Gravity.CENTER);

        // Indicates the current position of the grid is the previous month
        if(workingIndex <= firstDayOfMonth ) {
            today = prevDaysDisplayed + workingIndex;
            tv.setTextColor(Color.LTGRAY);
        }
        // Indicates the current position of the grid is the current month
        else if (workingIndex <= (daysInMonth + firstDayOfMonth)) {

            today = workingIndex - firstDayOfMonth;
            // It is the current day, let the user now
            if (today == dayOfMonth) {
                tv.setTextColor(Color.BLUE);
            }

            // TODO Make this check with event class to see if current day has events
            else if (today == 9 || today == 23) {
                tv.setTextColor(Color.RED);
            }

            // Normal day without events
            else {
                tv.setTextColor(Color.DKGRAY);
            }

        }

        // Otherwise the current position is outside of current month.
        else {
            today = workingIndex - firstDayOfMonth - daysInMonth;
            tv.setTextColor(Color.LTGRAY);
        }

        // Set the text into the grid cell
        tv.setText(Integer.toString(today));

        return tv;
    }

    public void changeCalendar(GregorianCalendar calendar) {
        this.calendar = calendar;
        setParams(calendar);
        notifyDataSetChanged();
        Log.d("changeCalendar", "The month is" + Integer.toString(this.calendar.get(GregorianCalendar.MONTH)));
    }
}
