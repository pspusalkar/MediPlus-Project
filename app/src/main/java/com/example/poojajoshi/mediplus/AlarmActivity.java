package com.example.poojajoshi.mediplus;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class AlarmActivity extends BaseActivity {
    AlarmCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        // init alarm database
        if ( !isAlarmDataInitialized() )
            initAlarmDataBase();

        // get list view handle and set the adapter
        ListView listView = findViewById(R.id.listView_alaramdetails);
        cursorAdapter = new AlarmCursorAdapter(AlarmActivity.this, getAlarmData(), 0);
        cursorAdapter.notifyDataSetChanged();
        listView.setAdapter(cursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // initially hide all the menu items
        hideMenu(menu);

        // show the menu items which are needed for this activity.
        menu.findItem(R.id.action_drug_details).setVisible(true);
        menu.findItem(R.id.action_add_alarm).setVisible(true);
        menu.findItem(R.id.action_alarm).setVisible(true);
        menu.findItem(R.id.action_shop).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // perform the requested action based of selected menu item.
        if (id == R.id.action_drug_details) {
            // navigate to the drug details activity.
            Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_add_alarm ) {
            // add new alarm and navigate to the add alarm activity
            Intent intent = new Intent(AlarmActivity.this, AddAlarmActivity.class);
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_alarm ) {
            /*
            Intent intent = new Intent(AlarmActivity.this, AddEditDrugActivity.class);
            intent.putExtra("Type", "Edit");
            startActivity(intent);
            */
            return true;
        } else if ( id == R.id.action_shop ) {
            // navigate to the drug shopping activity.
            Intent intent = new Intent(AlarmActivity.this, ShopDrugActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
