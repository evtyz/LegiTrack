package com.htn.legitrack;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable {
    public String id; // Open states id, never display!

    public String time; // Open States time for "latest action date"

    public String action; // What did the legislators actually do on the bill?

    public String title;

    public String summary; // abstract, java doesn't like the word 'abstract'

    public ArrayList<String> subjects = new ArrayList<>(); // could be empty

    public String legislature; // legislature where bill is presented

    public String publicID; // The actual government ID of the bill

    public ArrayList<String> sources = new ArrayList<>(); // URLs for source docs

    private interface JSONAttempt {
        void attempt() throws JSONException;
    }

    public static void parseJSON(JSONAttempt main, Runnable backup) {
        try {
            main.attempt();
        } catch (JSONException e) {
            backup.run();
            Log.e("Json", "Json parsing failed, error 2");
        }
    }

    public Bill(JSONObject rawBill) {

        parseJSON(() -> {
            id = rawBill.getString("id");
        }, () -> {
            id = "";
        });
        parseJSON(() -> {
            action = rawBill.getString("latest_action_description");
        }, () -> {
            action = "";
        });
        parseJSON(() -> {
            time = rawBill.getString("latest_action_date");
        }, () -> {
            time = "";
        });
        parseJSON(() -> {
            title = rawBill.getString("title");
        }, () -> {
            title = "";
        });
        parseJSON(() -> {
            summary = rawBill.getJSONArray("abstracts").getJSONObject(0).getString("abstract");
        }, () -> {
            summary = "";
        });
        parseJSON(() -> {
            JSONArray arr = rawBill.getJSONArray("subject");
            for (int i = 0; i < arr.length(); i++) {
                subjects.add(arr.getString(i));
            }
        }, () -> {
        });
        parseJSON(() -> {
            legislature = rawBill.getJSONObject("from_organization").getString("name");
        }, () -> {
            legislature = "";
        });
        parseJSON(() -> {
            publicID = rawBill.getString("identifier");
        }, () -> {
            publicID = "Unknown";
        });
        parseJSON(() -> {
            JSONArray arr = rawBill.getJSONArray("sources");
            for (int i = 0; i < arr.length(); i++) {
                sources.add(arr.getJSONObject(i).getString("url"));
            }
        }, () -> {
        });

        int i;
        for(i = 0; i < title.length(); i++){
            char x = (char)title.charAt(i);
            if(x == ',' || x == ';'){
                break;
                //summary = summary + x;
            }
        }
        String y1 = title.substring(0,i);
        String y2 = title.substring(i+2);
        title = y1;
        summary = y2;
    }
}
//