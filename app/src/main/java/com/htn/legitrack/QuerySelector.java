package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuerySelector extends AppCompatActivity {

    EditText searchbar;
    Button searchButton;
    Button nosearchButton;
    LinearLayout suggestionDisplay;

    String state;

    public static final String QUERY_REF = "query";

    public static ArrayList<String> suggestions = new ArrayList<>(Arrays.asList(
            "court", "water", "school", "rights", "coronavirus", "oil", "gas", "coal", "fuel", "property",
            "agriculture", "drugs", "crime", "energy", "guns", "health", "insurance", "labor", "recreation", "park", "transit", "welfare", "tax"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_selector);

        searchbar = findViewById(R.id.query_bar);
        searchButton = findViewById(R.id.gosearch_button);
        nosearchButton = findViewById(R.id.nosearch_button);

        suggestionDisplay = findViewById(R.id.suggestion_display);

        Collections.sort(suggestions);
        LayoutInflater layoutInflater = getLayoutInflater();

        for (String suggestion : suggestions) {
            View suggestionItem = layoutInflater.inflate(R.layout.suggestion_item, null);
            LinearLayout suggestionLayout = suggestionItem.findViewById(R.id.suggestion_layout);
            TextView suggestionLabel = suggestionItem.findViewById(R.id.suggestion_label);
            suggestionLabel.setText(suggestion);

            View.OnClickListener addSuggestion = view -> searchbar.setText(searchbar.getText().toString().concat(" ").concat(suggestion));
            suggestionLayout.setOnClickListener(addSuggestion);
            suggestionLabel.setOnClickListener(addSuggestion);
            suggestionItem.findViewById(R.id.suggestion_button).setOnClickListener(addSuggestion);
            suggestionDisplay.addView(suggestionItem, -1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }


        state = getIntent().getStringExtra(MainActivity.STATE_NAME);
    }

    public void search(View v) {
        // TODO: validate search results
        String rawQuery = searchbar.getText().toString();

        String[] terms = rawQuery.split("\\s+");

        if (terms.length == 1 && terms[0].equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a non-empty search query. Click PROCEED WITHOUT SEARCHING to find bills without a search.", Toast.LENGTH_LONG).show();
            return;
        } if (terms.length > 3) {
            Toast.makeText(getApplicationContext(), "Please enter 3 search terms or fewer.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(MainActivity.STATE_NAME, state);
        intent.putExtra(QUERY_REF, terms);
        startActivity(intent);

    }

    public void proceed(View v) {
        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(MainActivity.STATE_NAME, state);
        intent.putExtra(QUERY_REF, new String[]{});
        startActivity(intent);
    }
}