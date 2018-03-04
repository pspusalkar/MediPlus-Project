package com.example.poojajoshi.mediplus;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AlarmCursorAdapter extends CursorAdapter {
    public AlarmCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
        // super(context, c);
        c.moveToFirst();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // as its attached t parent no need to attach again.
        View retView = inflater.inflate(R.layout.alarm_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // here we are setting our data
        // that means, take the data from the cursor and put it in views

        // get the text view handles for alarm name, date and time.
        TextView textView_alarmName =(TextView) view.findViewById(R.id.textView_alarmName);
        TextView textView_alarmDate =(TextView) view.findViewById(R.id.textView_alarmDate);
        TextView textView_alarmTime =(TextView) view.findViewById(R.id.textView_alarmTime);

        // set the alarm name, date and time textview with the database data.
        textView_alarmName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        textView_alarmDate.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        textView_alarmTime.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
    }
}
