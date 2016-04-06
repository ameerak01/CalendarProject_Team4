package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.example.ameerak.calendarproject_team4.R.drawable.rounded_corners;

/**
 * Created by neil on 4/6/16.
 */
public class TextAdapter extends BaseAdapter {

    private Context context;
    private GregorianCalendar calendar;
    private GregorianCalendar prevMonthCalendar;
    private int dayOfWeek, dayOfMonth, daysInMonth,
            firstDayOfMonth, prevMonth, lengthPreviousMonth,
            prevDaysDisplayed;

    public TextAdapter(Context context) {
        this.context = context;
        calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        prevMonth = calendar.get(Calendar.MONTH) - 1;
        prevMonthCalendar = new GregorianCalendar(Calendar.YEAR, prevMonth,1);
        lengthPreviousMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);


        if(dayOfMonth == dayOfWeek) {
            firstDayOfMonth = 1;
        }
        else if (dayOfMonth < dayOfWeek) {
            firstDayOfMonth = dayOfMonth-dayOfWeek;
        }
        else {
            firstDayOfMonth = 7 - ((dayOfMonth-dayOfWeek)%7);
        }



        prevDaysDisplayed = lengthPreviousMonth - firstDayOfMonth;


    }

    public int getCount() {
        return 42;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        int today, workingIndex = position + 1;
        if (convertView == null) {
            tv = new TextView(context);
            tv.setLayoutParams(new GridView.LayoutParams(85, 85));
        }
        else {
            tv = (TextView) convertView;
        }

        //tv.setBackgroundResource(rounded_corners);

        if(workingIndex <= firstDayOfMonth ) {
            today = prevDaysDisplayed + workingIndex;
            tv.setTextColor(Color.LTGRAY);
            //tv.setBackgroundColor(Color.LTGRAY);
        }
        else if (workingIndex <= (daysInMonth + firstDayOfMonth)) {
            today = workingIndex - firstDayOfMonth;
            if (today == dayOfMonth) {
                tv.setTextColor(Color.BLUE);
            }
            else {
                tv.setTextColor(Color.DKGRAY);
            }

        }
        else {
            today = workingIndex - firstDayOfMonth - daysInMonth;
            tv.setTextColor(Color.LTGRAY);
            //tv.setBackgroundColor(Color.DKGRAY);
        }

        tv.setText(Integer.toString(today));
        return tv;
    }
}
