package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;
import java.util.Set;

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
        ArrayList<String> topPref = getTopThreePreferences();
        for (String pref : topPref) {
            int index = suggestions.indexOf(pref);
            if (index != -1) {
                suggestions.remove(index);
            }
            suggestions.add(0, pref);
        }

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

    public ArrayList<String> getTopThreePreferences() {
        ArrayList<String> topPref = new ArrayList<>();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        for (String key : new String[] {"third", "second", "first"}) {
            String val = sp.getString(key, "");
            if (!val.equals("")) {
                topPref.add(val);
            }
        }
        return topPref;
    }

    public void setTopThreePreferences(List<String> terms) {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("save", Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        for (String term : terms) {
            insertTerm(term, sp, spe);
            spe.commit();
        }
//        String first = sp.getString("first", "");
//        String second = sp.getString("second", "");
//        String third = sp.getString("third","");
//        String fourth = sp.getString("fourth", "");

    }

    private void insertTerm(String term, SharedPreferences sp, SharedPreferences.Editor spe) {
        String first = sp.getString("first", "");
        String second = sp.getString("second", "");
        spe.putString("third", second);
        spe.putString("second", first);
        spe.putString("first", term);
    }

    public void search(View v) {
        // TODO: validate search results
        String rawQuery = searchbar.getText().toString();
        List<String> terms = new ArrayList<>(Arrays.asList(rawQuery.split("\\s+")));
        if (terms.get(0).equals("")) {
            terms.remove(0);
        }
        if (terms.size() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter a non-empty search query. Click PROCEED WITHOUT SEARCHING to find bills without a search.", Toast.LENGTH_LONG).show();
            return;
        } if (terms.size() > 3) {
            Toast.makeText(getApplicationContext(), "Please enter 3 search terms or fewer.", Toast.LENGTH_SHORT).show();
            return;
        }

        setTopThreePreferences(terms);
        String[] termArray = terms.toArray(new String[terms.size()]);

        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(MainActivity.STATE_NAME, state);
        intent.putExtra(QUERY_REF, termArray);
        startActivity(intent);

    }

    public void proceed(View v) {
        Intent intent = new Intent(getApplicationContext(), BillDisplay.class);
        intent.putExtra(MainActivity.STATE_NAME, state);
        intent.putExtra(QUERY_REF, new String[]{});
        startActivity(intent);
    }
}