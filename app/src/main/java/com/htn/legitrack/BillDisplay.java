package com.htn.legitrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BillDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_display);
    }

    // Queries the API for latest bills of a particular state
    // Add more parameters for filters later...
    public void query(String state) {

    }
}