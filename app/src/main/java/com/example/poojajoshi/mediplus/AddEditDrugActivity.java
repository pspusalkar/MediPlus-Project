package com.example.poojajoshi.mediplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditDrugActivity extends BaseActivity {
    String activityType;
    DrugDetails drug = new DrugDetails();
    EditText drugName, drugDescription, drugPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug);

        // get the text header handle.
        TextView headerText = findViewById(R.id.textView_header);

        // activity type to differentiate between add drug and edit drug
        activityType = getIntent().getStringExtra("Type");

        // get the handle of drug name, description and price edit text.
        drugName = findViewById(R.id.editText_drugName);
        drugDescription = findViewById(R.id.editText_drugDescriptiom);
        drugPrice = findViewById(R.id.editText_drugPrice);

        // Toast.makeText(AddEditDrugActivity.this, activityType, Toast.LENGTH_SHORT).show();

        // based on the activity type change the header text.
        if ( activityType.equals("Add") ) {
            headerText.setText("Add Drug Details");
        } else if ( activityType.equals("Edit") ) {
            headerText.setText("Edit Drug Details");

            // fill the drug data handle with values passed from previous activity.
            drug.setID(Integer.parseInt(getIntent().getStringExtra("id")));
            drug.setDrug_name(getIntent().getStringExtra("name"));
            drug.setDrug_description(getIntent().getStringExtra("description"));
            drug.setDrug_price(getIntent().getStringExtra("price"));

            // set the data into the fields
            drugName.setText(getIntent().getStringExtra("name"));
            drugDescription.setText(getIntent().getStringExtra("description"));
            drugPrice.setText(getIntent().getStringExtra("price"));

            // String str = String.valueOf(drug.getID()) + drugName.getText().toString() + drugDescription.getText().toString() + drugPrice.getText().toString();
            // Toast.makeText(AddEditDrugActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // initially hide all the menu items.
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
            // save the changed drug data to database.
            DrugDetails updatedDrug = new DrugDetails();
            updatedDrug.setDrug_name(drugName.getText().toString());
            updatedDrug.setDrug_description(drugDescription.getText().toString());
            updatedDrug.setDrug_price(drugPrice.getText().toString());

            String str = String.valueOf(updatedDrug.getID()) + drugName.getText().toString() + drugDescription.getText().toString() + drugPrice.getText().toString();
            Toast.makeText(AddEditDrugActivity.this, str, Toast.LENGTH_SHORT).show();

            if ( activityType.equals("Add") ) {
                // add new activity and show the same in drug details activity
                // save the data to database
                addDrug(updatedDrug);

                Intent intent = new Intent(AddEditDrugActivity.this, MainActivity.class);
                startActivity(intent);
            } else if ( activityType.equals("Edit") ) {
                // after drug edit, show the drug data.
                updatedDrug.setID(drug.getID());

                // save the data to database
                saveDrugData(updatedDrug);

                // navigate to the drug data activity
                Intent intent = new Intent(AddEditDrugActivity.this, DrugDataActivity.class);
                intent.putExtra("id", drug.getID());
                intent.putExtra("name", drug.getDrug_name());
                intent.putExtra("description", drug.getDrug_description());
                intent.putExtra("price", drug.getDrug_price());
                startActivity(intent);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
