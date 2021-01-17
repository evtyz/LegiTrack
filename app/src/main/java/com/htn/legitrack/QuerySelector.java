package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuerySelector extends AppCompatActivity {

    EditText searchbar;
    Button searchButton;
    Button nosearchButton;

    String state;

    public static final String QUERY_REF = "query";

    public static final String[] suggestions = new String[] {
            // Add suggestions here...
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_selector);

        searchbar = findViewById(R.id.query_bar);
        searchButton = findViewById(R.id.gosearch_button);
        nosearchButton = findViewById(R.id.nosearch_button);

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