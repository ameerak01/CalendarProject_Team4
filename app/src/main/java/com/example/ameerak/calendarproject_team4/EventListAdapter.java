package com.example.ameerak.calendarproject_team4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ameerak.calendarproject_team4.business_objects_layer.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventListAdapter extends ArrayAdapter<Event> {

    // Formatting specifications (Ex. Fri, April 8, 2016)
    private final SimpleDateFormat mSdfDate = new SimpleDateFormat("MMMM d, yyyy");
    // Formatting specifications (Ex. 9:45 AM)
    private final SimpleDateFormat mSdfTime = new SimpleDateFormat("h:mm a");

    public EventListAdapter(Context context, int resource, ArrayList<Event> events) {
        super(context, resource, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.event_list_item, null);
        }

        Event event = getItem(position);

        TextView dateView = (TextView) v.findViewById(R.id.event_date);
        dateView.setText(mSdfDate.format(event.getEventStartTime().getTime()));

        TextView eventNameView = (TextView) v.findViewById(R.id.event_name);
        eventNameView.setText(event.getTitle());

        TextView eventStartView = (TextView) v.findViewById(R.id.event_start_time);
        eventStartView.setText(mSdfTime.format(event.getEventStartTime().getTime()));

        TextView eventEndView = (TextView) v.findViewById(R.id.event_end_time);
        eventEndView.setText(mSdfTime.format(event.getEventEndTime().getTime()));

        return v;
    }
}