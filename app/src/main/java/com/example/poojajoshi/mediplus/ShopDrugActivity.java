package com.example.poojajoshi.mediplus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ShopDrugActivity extends BaseActivity {
    String[] drugNameList = {"Crocine", "Thyroxin", "Benedryal", "Prospan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_drugs);

        // init alarm database
        initAlarmDataBase();

        // get list view handle and set the adapter
        ListView listView = findViewById(R.id.listView_shopdrugs);
        ShopDrugAdapter adapter = new ShopDrugAdapter(this, drugNameList);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        // set on item click listener
        // on item on click navigate to the website to shop the drug
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = "https://www.amazon.in";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // initially hide all the menu items
        hideMenu(menu);

        // show the menu items which are needed for this activity.
        menu.findItem(R.id.action_drug_details).setVisible(true);
        menu.findItem(R.id.action_alarm).setVisible(true);

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
            Intent intent = new Intent(ShopDrugActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_alarm ) {
            // navigate to the alarms activity
            Intent intent = new Intent(ShopDrugActivity.this, AlarmActivity.class);
            intent.putExtra("Type", "Edit");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
