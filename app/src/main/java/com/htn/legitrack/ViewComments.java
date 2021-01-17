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

public class ViewComments extends AppCompatActivity {

    private static final String TAG = "ViewComments";
    String testComment;
    TextView urlView;
    Bill bill;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    public TextView newtextview2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_comments);

        urlView = findViewById(R.id.newtextview1);
        Intent intent = getIntent();
        bill = (Bill)intent.getSerializableExtra("bill");
        loadBill(bill);

        //newtextview1.setText(testComment);
        //newBill = (Bill) getIntent().getSerializableExtra("Bill");

        newtextview2 = (TextView) findViewById(R.id.newtextview2);

        // Read from the database
        //myRef.addValueEventListener(new ValueEventListener() {
        /*ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                DBobjects myObj;
                myObj = dataSnapshot.getValue(DBobjects.class);
                //Log.d(TAG, "Comment: " + myObj.comments);

                testComment = myObj.getComments();
                newtextview2.setText(testComment);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        };
        myRef.addValueEventListener(postListener);*/
    }
    private void loadBill(Bill bill) {
        urlView.setText(bill.publicID);
    }

    public void sendComment(View view) {
        Intent intent = new Intent(getApplicationContext(), Reaction.class);
        intent.putExtra("Bill", bill);
        startActivity(intent);

    }
}
