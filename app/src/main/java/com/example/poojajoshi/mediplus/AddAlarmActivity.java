package com.example.poojajoshi.mediplus;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddAlarmActivity extends BaseActivity {
    EditText alarmName, alarmDate, alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);

        // get the handle for alarm name, date and time.
        alarmName = findViewById(R.id.editText_alarmName);
        alarmDate = findViewById(R.id.editText_alarmDate);
        alarmTime = findViewById(R.id.editText_alarmTime);

        // get the set date handle and set the onclick listener
        Button dateBtn = findViewById(R.id.button_setData);
        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create datepicker listener and date picker dialog to be shown on set Date click.
                // the selected date will be reflected into the alarm date edittext.
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String date = String.valueOf(i2) + "/" + String.valueOf(i1) + "/" + String.valueOf(i);
                        // set the selected date to alarmdate edittext
                        alarmDate.setText(date);
                    }
                };

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DATE);

                DatePickerDialog dateDialog = new DatePickerDialog(AddAlarmActivity.this, listener, year, month, day);
                dateDialog.show();
            }
        });

        // get the handle of set time button and set the onclick listener for the same.
        Button timeBtn = findViewById(R.id.buttonsetTime);
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String time = String.valueOf(i) + ":" + String.valueOf(i1);
                        alarmTime.setText(time);
                    }
                };

                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timeDialog = new TimePickerDialog(AddAlarmActivity.this, timeListener, hour, minute, false);
                timeDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // initially hide all menu items.
        hideMenu(menu);

       // show only those menu items which are needed for this activity.
        menu.findItem(R.id.action_save).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // perform the requested action based of selected menu item.
        if (id == R.id.action_save) {
            // add new alarm and show the same in alarm list activity
            AlarmDetails alarm = new AlarmDetails();
            alarm.setAlarm_name(alarmName.getText().toString());
            alarm.setAlarm_date(alarmDate.getText().toString());
            alarm.setAlarm_time(alarmTime.getText().toString());

            // add alarm to database.
            addAlarm(alarm);

            // navigate to the alarm list activity.
            Intent intent = new Intent(AddAlarmActivity.this, AlarmActivity.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
