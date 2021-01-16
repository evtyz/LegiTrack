package com.htn.legitrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;

public class Reaction extends AppCompatActivity {

    private static final String TAG = "Reaction";
    EditText reactionText;
    //private static final String TAG = Reaction.class.getSimpleName();
    DatabaseReference myRef;
    SeekBar reactionRating;
    int valInt;
    String comment;

    Bill newBill;
    //Bill newBill = (Bill) getIntent().getSerializableExtra("MyClass");

    //Test code for pushing objects to the DB
    //DBobjects testObj1 = new DBobjects("13oin4f","this bill sucks!!!",1);
    //DBobjects testObj2 = new DBobjects("q983nr","this bill rocks!!!",5);
    //DBobjects testObj3 = new DBobjects("1p9387","meh",0);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_page);

        //Intent intent = getIntent();
        newBill = (Bill) getIntent().getSerializableExtra("Bill");
    }


    // Push comment and score to DB when button is clicked
    public void pushtoDB(View view) {

        //manage text first
        reactionText = (EditText) findViewById(R.id.reaction_test);
        comment = reactionText.getText().toString();

        //now seekBar
        SeekBar mySeek = (SeekBar) findViewById(R.id.seekBar);
        valInt = mySeek.getProgress();

        //set up seekBar listener
        mySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        //set the comments and score for the object
        //testObj3.setComments(comment);
        //testObj3.setScore(valInt);
        DBobjects testObj = new DBobjects(newBill);
        testObj.setComments(comment);
        testObj.setScore(valInt);

        //push all 3 objects to the DB
        //myRef.push().setValue(testObj1);
        //myRef.push().setValue(testObj2);
        //myRef.push().setValue(testObj3);

        myRef.push().setValue(testObj);

        finish();
        }
    }




