package com.example.poojajoshi.mediplus;

import android.widget.CursorAdapter;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

public class DrugCursorAdapter extends CursorAdapter {

    public DrugCursorAdapter(Context context, Cursor c, int flags) {
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
        View retView = inflater.inflate(R.layout.drug_item, parent, false);

        return retView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // here we are setting our data
        // that means, take the data from the cursor and put it in views

        // get the handle for drug name, description and price textview
        TextView textView_drugName =(TextView) view.findViewById(R.id.textView_drugName);
        TextView textView_drugDescription =(TextView) view.findViewById(R.id.textView_drugDescription);
        TextView textView_drugPrice =(TextView) view.findViewById(R.id.textView_price);

        // set the database data to texview handles
        textView_drugName.setText("Name : " + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        textView_drugDescription.setText("Descritpion : " + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
        textView_drugPrice.setText("Price : " + cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
    }
}
