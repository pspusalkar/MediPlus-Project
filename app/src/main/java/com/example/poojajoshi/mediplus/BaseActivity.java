package com.example.poojajoshi.mediplus;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

// BaseActivity is the base class for all activities in this application.
// BaseActivity creates the handle to database which is used by all the activities in this application.
// Also, creates all the menu items to be displayed in all the activities.

public class BaseActivity extends AppCompatActivity {
    public static AlarmDrugDatabase database = null;
    public static boolean isAlaramDataBaseInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        // Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // setting Toolbar as Action Bar for the App
        // setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void initDrugDataBase() {
        // creates the data base handle and adds dummy drug data to database.
        database = new AlarmDrugDatabase(this);
        SQLiteDatabase sqDatabase = database.getWritableDatabase();

        database.addDrug(new DrugDetails("Drug1", "Headache", "2.0"));
        database.addDrug(new DrugDetails("Drug1", "Headache", "2.0"));
        database.addDrug(new DrugDetails("Drug1", "Headache", "2.0"));
    }

    public void initAlarmDataBase() {
        // add the dummy alarm data to database.

        isAlaramDataBaseInitialized = true;
        database.addAlarm(new AlarmDetails("Alarm", "22/02/2018", "2.00"));
        database.addAlarm(new AlarmDetails("Alarm", "22/02/2018", "2.00"));
        database.addAlarm(new AlarmDetails("Alarm", "22/02/2018", "2.00"));
    }

    public boolean isDataBaseInitialized() {
        return database == null ? false : true;
    }

    public boolean isAlarmDataInitialized() {
        return isAlaramDataBaseInitialized;
    }

    public void hideMenu(Menu menu) {
        // hide all menu items. Individual activities will mark them visible.
        for ( int i = 0; i < menu.size(); i++ ) {
            MenuItem item = menu.getItem(i);
            item.setVisible(false);
        }
    }

    public DrugDetails getDrugData(int id) {
        // get the drug data for specified index.
        return  database.getDrug(id);
    }

    public void saveDrugData(DrugDetails drug) {
        // update the drug data
        String str = drug.getDrug_name() + drug.getDrug_description() + drug.getDrug_price();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        database.updateDrug(drug);
    }

    public AlarmDetails getAlarmData(int id) {
        // get the alarm data for specified index.
        return  database.getAlarm(id);
    }

    public void saveAlarmData(AlarmDetails alarm) {
        // update alarm data
        database.updateAlarm(alarm);
    }

    public Cursor getDrugData() {
        // get all drugs data
        return database.getAllDrugData();
    }

    public Cursor getAlarmData() {
        // get all alarm data
        return database.getAllAlarmData();
    }

    public void addAlarm(AlarmDetails alarmDetails) {
        // add new alarm to database.
        database.addAlarm(alarmDetails);
    }

    public void addDrug(DrugDetails drugDetails) {
        // add new drug to database.
        database.addDrug(drugDetails);
    }
}
