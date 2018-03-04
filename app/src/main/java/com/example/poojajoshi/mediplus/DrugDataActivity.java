package com.example.poojajoshi.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class DrugDataActivity extends BaseActivity {
    DrugDetails drug = new DrugDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug);

        // get the textview header handle and update the text.
        TextView headerText = findViewById(R.id.textView_header);
        headerText.setText("Drug Data");

        // get handle for drug name, description and price edit text
        EditText editName = findViewById(R.id.editText_drugName);
        EditText editDescription = findViewById(R.id.editText_drugDescriptiom);
        EditText editPrice = findViewById(R.id.editText_drugPrice);

        // make all entries as read only. do not allow to edit.
        editName.setKeyListener(null);
        editName.setClickable(false);
        editDescription.setKeyListener(null);
        editDescription.setClickable(false);
        editPrice.setKeyListener(null);
        editPrice.setClickable(false);

        // set the drug info.
        // drug.setID(Integer.parseInt(getIntent().getStringExtra("id")));
        drug.setID(Integer.parseInt(getIntent().getStringExtra("id")));
        drug.setDrug_name(getIntent().getStringExtra("name"));
        drug.setDrug_description(getIntent().getStringExtra("description"));
        drug.setDrug_price(getIntent().getStringExtra("price"));

        // set the drug data into all fields.
        editName.setText(drug.getDrug_name());
        editDescription.setText(drug.getDrug_description());
        editPrice.setText(drug.getDrug_price());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // initially hide all the menu items
        hideMenu(menu);

        // show only those menu items which are needed for this activity.
        menu.findItem(R.id.action_drug_details).setVisible(true);
        menu.findItem(R.id.action_share).setVisible(true);
        menu.findItem(R.id.action_edit).setVisible(true);
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
            // navigate to the drug details activity
            Intent intent = new Intent(DrugDataActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_share ) {
            // share the drug on social media
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            startActivity(intent);
            return true;
        } else if ( id == R.id.action_edit ) {
            //  navigate to the edit drug activity.
            Intent intent = new Intent(DrugDataActivity.this, AddEditDrugActivity.class);
            intent.putExtra("Type", "Edit");
            intent.putExtra("id", String.valueOf(drug.getID()));
            intent.putExtra("name", drug.getDrug_name());
            intent.putExtra("description", drug.getDrug_description());
            intent.putExtra("price", drug.getDrug_price());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
