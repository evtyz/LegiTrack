package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InterestChooser extends AppCompatActivity {

    public String state;

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
    }
}