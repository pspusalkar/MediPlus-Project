package com.example.poojajoshi.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    DrugCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // in it drug database.
        if ( ! isDataBaseInitialized() )
            initDrugDataBase();

        // get list view handle and set the adapter
        ListView listView = findViewById(R.id.listView_drug_details);
        cursorAdapter = new DrugCursorAdapter(MainActivity.this, getDrugData(), 0);
        cursorAdapter.notifyDataSetChanged();
        listView.setAdapter(cursorAdapter);

        // set item on click listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                // on list item click navigate to the drug data activity.
                DrugDetails drug = getDrugData(i+1);

                Intent intent = new Intent(MainActivity.this, DrugDataActivity.class);
                intent.putExtra("id", String.valueOf(drug.getID()));
                intent.putExtra("name", drug.getDrug_name());
                intent.putExtra("description", drug.getDrug_description());
                intent.putExtra("price", drug.getDrug_price());

                // start the drugdata activity
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        hideMenu(menu);

        // Hide the menu items which are not needed for this activity.
        menu.findItem(R.id.action_addDrug).setVisible(true);
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
        if (id == R.id.action_addDrug) {
            // navigate to add drug activity
            Intent intent = new Intent(MainActivity.this, AddEditDrugActivity.class);
            intent.putExtra("Type", "Add");
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_alarm ) {
            // navigate to alarm list activity
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_shop ) {
            // navigate to shop activity.
            Intent intent = new Intent(MainActivity.this, ShopDrugActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
