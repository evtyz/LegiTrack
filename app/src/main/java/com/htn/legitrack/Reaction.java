package com.htn.legitrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Reaction extends AppCompatActivity {

    EditText reactionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_page);

        reactionText = (EditText) findViewById(R.id.reaction_test);

    }

}
