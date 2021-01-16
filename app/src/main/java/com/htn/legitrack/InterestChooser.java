package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InterestChooser extends AppCompatActivity {

    //public static final String INTEREST_NAME = "com.htn.legitrack.INTEREST_NAME";
    public String state;
    String interestName;
    Spinner dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_chooser);

        //read in selected state information
        //Intent intent = getIntent();
        //state = intent.getStringExtra(MainActivity.STATE_NAME);
        //Log.d("test", state);

        //set spinner item id
        dialog = findViewById(R.id.dialog);

        String[] interestList;
        interestList = new String[] {"Agriculture and Food", "Animal Rights and Wildlife Issues", "Arts and Humanities",
                        "Budget, Spending, and Taxes", "Business and Consumers", "Campaign Finance and Election Issues",
                        "Civil Liberties and Civil Rights", "Commerce", "Crime", "Drugs", "Education", "Energy",
                        "Environmental", "Executive Branch", "Family and Children Issues", "Federal, State, and Local Relations",
                        "Gambling and Gaming", "Government Reform", "Guns", "Health", "Housing and Property", "Immigration",
                        "Indigenous Peoples", "Insurance", "Judiciary", "Labor and Employment", "Legal Issues", "Legislative Affairs",
                        "Military", "Municipal and County Issues", "Nominations", "Other", "Public Services", "Recreation",
                        "Reproductive Issues", "Resolutions", "Science and Medical Research", "Senior Issues",
                        "Sexual Orientation and Gender Issues", "Social Issues", "State Agencies", "Subject",
                        "Technology and Communication", "Trade", "Transportation", "Welfare and Poverty"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, interestList);

        //set the spinners adapter
        dialog.setAdapter(adapter);

        // TODO: add interests
    }

    public void sendInterest(View view) {
        interestName = dialog.getSelectedItem().toString();
        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        //intent.putExtra(INTEREST_NAME, interestName);
        startActivity(intent);

    }
}