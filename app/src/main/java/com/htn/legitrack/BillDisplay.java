package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BillDisplay extends AppCompatActivity {

    BillListAdapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<Bill> billList;
    RecyclerView recyclerView;
    String state;
    ArrayList<String> interests;

    EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_display);

        //Joe's test
        Intent intent = getIntent();
        state = intent.getStringExtra(MainActivity.STATE_NAME);
        interests = intent.getStringArrayListExtra(InterestChooser.INTEREST_NAME);
        Log.d("test", state);
        billList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new BillListAdapter(getApplicationContext(), state, interests);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                adapter.loadBills();
            }
        };

        recyclerView.addOnScrollListener(scrollListener);
    }
}