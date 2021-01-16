package com.htn.legitrack;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bill {
    public String id; // Open states id, never display!

    public String time; // Open States time for "latest action date"

    public String action; // What did the legislators actually do on the bill?

    public String title;

    public String summary; // abstract, java doesn't like the word 'abstract'

    public String[] subjects; // could be empty

    public String legislature; // legislature where bill is presented

    public String publicID; // The actual government ID of the bill

    public String[] sources; // URLs for source docs

    public Bill(JSONObject rawBill) {
    try {
        id = rawBill.getString("id");

        time = rawBill.getString("latest_action_date");

        action = rawBill.getString("latest_action_description");

        title = rawBill.getString("title");

        summary = rawBill.getString("abstract");

        JSONArray arr = rawBill.getJSONArray("subject");
        for (int i = 0; i < arr.length(); i++) {
            subjects[i] = arr.getJSONObject(i).getString("subject");
        }

        rawBill.getJSONObject("jurisdiction").getString("classification");

        publicID = rawBill.getString("identifier");

        JSONArray arr2 = rawBill.getJSONArray("openstates_url");
        for (int i = 0; i < arr2.length(); i++) {
            sources[i] = arr2.getJSONObject(i).getString("subject");
        }
    }
    catch (JSONException e) {
        Log.e("JSON", "Json error 2");
    }
    }
}
//