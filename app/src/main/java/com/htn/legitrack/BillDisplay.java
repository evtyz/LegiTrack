package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

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

    RequestQueue queue;
    ArrayList<JSONObject> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_display);
        queue = Volley.newRequestQueue(this);
        billList = new ArrayList<>();
    }

    // Queries the API for latest bills of a particular state
    // Add more parameters for filters later...
    public void query(StateUS state) {
        String url = "https://v3.openstates.org/bills?jurisdiction=".concat(state.unabbreviated).concat("&apikey=9341f3e4-6ae0-4d2d-b498-9f1d9c0ff8a6"); // TODO
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject bill = results.getJSONObject(i);
                        billList.add(bill);
                        // TODO: do something to the bill
                    }
                } catch (JSONException e) {
                    Log.e("JSON", "Json error");
                }
            }
        }, error -> Log.e("Volley", "Volley error"));
        queue.add(request);
    }
}