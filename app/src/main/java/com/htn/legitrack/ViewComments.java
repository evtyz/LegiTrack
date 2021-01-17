package com.htn.legitrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewComments extends AppCompatActivity {

    private static final String TAG = "ViewComments";
    String testComment = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    public TextView newtextview1;
    public TextView newtextview2;

    List<DBobjects> myList = new ArrayList<DBobjects>();
    Bill newBill;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_comments);

        Intent intent = getIntent();
        newBill = (Bill) intent.getSerializableExtra("thisBill");
        //Intent intent = getIntent();
        //newBill = (Bill) getIntent().getSerializableExtra("Bill");

        newtextview1 = (TextView) findViewById(R.id.newtextview1);
        newtextview2 = (TextView) findViewById(R.id.newtextview2);

        newtextview1.setText(newBill.publicID);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            //ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String key = ds.getKey();
            DBobjects testObj = new DBobjects();

            testObj.setComments(ds.getValue(DBobjects.class).getComments());
            testObj.setScore(ds.getValue(DBobjects.class).getScore());
            testObj.setId(ds.getValue(DBobjects.class).getId());

            Log.d(TAG, testObj.getComments());
            Log.d(TAG, String.valueOf(testObj.getScore()));
            Log.d(TAG, testObj.getId());

            if (newBill.id.equals(testObj.id)) {
                myList.add(testObj);
                Log.d(TAG, "it worked");
            }

            for (int i = 0; i < myList.size(); i++ )
            {
                testComment = testComment + "\n\n" + myList.get(i).comments;
                Log.d(TAG, testComment);
                newtextview2.setText(testComment);
            }
            }
    }

    public void seeBillInfo(View view) {
        Intent intent = new Intent(getApplicationContext(), BillInfo.class);
        intent.putExtra("Bill", newBill);
        startActivity(intent);

    }
}
