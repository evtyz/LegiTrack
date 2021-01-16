package com.htn.legitrack;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

public class Reaction extends AppCompatActivity {

    private static final String TAG = "Reaction";
    EditText reactionText;
    //private static final String TAG = Reaction.class.getSimpleName();
    DatabaseReference myRef;
    SeekBar reactionRating;
    int valInt;

    //Test code for pushing objects to the DB

    //JSONObject myBilljson = new JSONObject();
    //Bill myBilljava = new Bill(myBilljson);
    DBobjects testObj1 = new DBobjects("13oin4f","this bill sucks!!!",1);
    DBobjects testObj2 = new DBobjects("q983nr","this bill rocks!!!",5);
    DBobjects testObj3 = new DBobjects("1p9387","meh",0);
    //

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.reaction_page);

    //myBilljava.id = "123456" ;
    //myBilljava.title = "something else";


        //reactionText = (EditText) findViewById(R.id.reaction_test);

    }

    /*public void basicReadWrite() {
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        // [END write_message]

        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // [END read_message]
    }*/

    public void pushtoDB(View view) {
        // Push comment to DB
            reactionText = (EditText) findViewById(R.id.reaction_test);
            //myRef.child("comment").setValue("Did it work?");
            //reactionRating = (SeekBar) findViewById(R.id.seekBar);

            SeekBar mySeek = (SeekBar) findViewById(R.id.seekBar);

        String comment = reactionText.getText().toString();
        valInt = mySeek.getProgress();


        mySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
          public void onProgressChanged(SeekBar seekBar, int progress,
                                        boolean fromUser) {
              String valStr = String.valueOf(progress);
              //valInt = Integer.parseInt(valStr);
              //valInt = mySeek.getProgress();
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

        //myRef.setValue(comment);
        //myRef.setValue(myBilljava);

        testObj3.setComments(comment);
        testObj3.setScore(valInt);

        myRef.push().setValue(testObj1);
        myRef.push().setValue(testObj2);
        myRef.push().setValue(testObj3);

        }

        }




