package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class InterestChooser extends AppCompatActivity {

    public static final String INTEREST_NAME = "interests";

    String state;
    String interestName;
    LinearLayout interestDisplay;
    LayoutInflater layoutInflater;
    ArrayList<View> interestList = new ArrayList<>();

    public final String[] INTEREST_LIST = new String[]
            {"Agriculture and Food", "Animal Rights and Wildlife Issues", "Arts and Humanities",
                    "Budget, Spending, and Taxes", "Business and Consumers", "Campaign Finance and Election Issues",
                    "Civil Liberties and Civil Rights", "Commerce", "Crime", "Drugs", "Education", "Energy",
                    "Environmental", "Executive Branch", "Family and Children Issues", "Federal, State, and Local Relations",
                    "Gambling and Gaming", "Government Reform", "Guns", "Health", "Housing and Property", "Immigration",
                    "Indigenous Peoples", "Insurance", "Judiciary", "Labor and Employment", "Legal Issues", "Legislative Affairs",
                    "Military", "Municipal and County Issues", "Nominations", "Other", "Public Services", "Recreation",
                    "Reproductive Issues", "Resolutions", "Science and Medical Research", "Senior Issues",
                    "Sexual Orientation and Gender Issues", "Social Issues", "State Agencies", "Subject",
                    "Technology and Communication", "Trade", "Transportation", "Welfare and Poverty"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_chooser);
        // TODO: add interests

        Intent intent = getIntent();
        state = intent.getStringExtra(MainActivity.STATE_NAME);

        interestDisplay = findViewById(R.id.interest_display);
        layoutInflater = getLayoutInflater();

        for (String interest : INTEREST_LIST) {
            View interestItem = layoutInflater.inflate(R.layout.interest_item, null);

            LinearLayout interestLayout = interestItem.findViewById(R.id.interest_item);
            CheckBox check = interestItem.findViewById(R.id.interest_check);
            interestLayout.setOnClickListener(view -> check.setChecked(!check.isChecked()));

            TextView interestLabel = interestItem.findViewById(R.id.interest_label);
            interestLabel.setText(interest);

            interestDisplay.addView(interestItem, -1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            interestList.add(interestItem);
        }
    }

    public void sendInterest(View view) {
        //interestName = scrollMenu.getSelectedItem().toString();
        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(MainActivity.STATE_NAME, state);

        ArrayList<String> interests = getInterests();
        intent.putExtra(INTEREST_NAME, interests);
        //intent.putExtra(INTEREST_NAME, interestName);
        startActivity(intent);

    }

    private ArrayList<String> getInterests() {
        ArrayList<String> interests = new ArrayList<>();
        for (View v : interestList) {
            CheckBox check = v.findViewById(R.id.interest_check);
            if (check.isChecked()) {
                TextView textView = v.findViewById(R.id.interest_label);
                interests.add(textView.getText().toString());
            }
        }
        return interests;
    }
}