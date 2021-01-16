package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static final String STATE_NAME = "com.htn.legitrack.STATE_NAME";
    String stateName;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Comment
        //Joe's test comment

        //define a spinner that is the same as the one declared in XML
        dropdown = findViewById(R.id.spinner1);
        //

        //dropdown.setOnItemSelectedListener(new MySpinnerSelectedListener());

//create a list of states for the spinner
        String[] items = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};


//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    /*public class MySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String selected = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }*/

    public void sendState(View view) {
        stateName = dropdown.getSelectedItem().toString();
        //String stateName = "North Carolina";
        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(STATE_NAME, stateName);
        startActivity(intent);

    }

    public void gotoreaction(View view) {
        Intent intent = new Intent(getApplicationContext(), Reaction.class);
        startActivity(intent);

    }

    public void gotointerest(View view) {
        Intent intent = new Intent(getApplicationContext(), InterestChooser.class);
        startActivity(intent);
    }
}