package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Bill> billList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_display);

        //Joe's test
        Intent intent = getIntent();
        String myState = intent.getStringExtra(MainActivity.STATE_NAME);
        Log.d("test",myState);
        billList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new BillListAdapter(getApplicationContext(), myState);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


}