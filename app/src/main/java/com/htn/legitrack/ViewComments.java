package com.htn.legitrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewComments extends AppCompatActivity {

    private static final String TAG = "ViewComments";
    String comment;
    TextView urlView;
    Bill bill;
    LinearLayoutManager layoutManager;
    ArrayList<String> commentList;
    RecyclerView recyclerView2;
    EndlessRecyclerViewScrollListener scrollListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    public TextView newtextview2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_comments);

        urlView = findViewById(R.id.newtextview1);
        Intent intent = getIntent();
        comm = (String)intent.getSerializableExtra("comment");
        loadComm(comment);

        commentList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);

        //comment = intent.getStringExtra(MainActivity.STATE_NAME);
        comment = "test";
        //queryTerms = intent.getStringArrayExtra(QuerySelector.QUERY_REF);
        //Log.d("test", state);
        recyclerView2 = findViewById(R.id.recycler_view);
        //adapter = new BillListAdapter(getApplicationContext(), state, queryTerms);
        layoutManager = new LinearLayoutManager(this);

        //recyclerView.setAdapter(adapter);
        recyclerView2.setLayoutManager(layoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //adapter.loadBills();
            }
        };

        recyclerView2.addOnScrollListener(scrollListener);

        //newtextview1.setText(testComment);
        //Intent intent = getIntent();
        //newBill = (Bill) getIntent().getSerializableExtra("Bill");

        //newtextview2 = (TextView) findViewById(R.id.newtextview2);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            //ValueEventListener postListener = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //testObj = dataSnapshot.getValue(DBobjects.class);
                showData(dataSnapshot);
                //testObj = dataSnapshot.getChildren();
                //Log.d(TAG, "Comment: " + testObj.comments);

                //long count = dataSnapshot.getChildrenCount();
                //Log.d(TAG, String.valueOf(count));

                //boolean yayornay = dataSnapshot.exists();
                //Log.d(TAG, Boolean.toString(yayornay));

                //testComment = testObj.getComments();
                //newtextview2.setText(testComment);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void loadBill(Bill bill) {
        urlView.setText(bill.publicID);
    }

    public void showData(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    DBobjects testObj = new DBobjects();
                    testObj.setComments(ds.getValue(DBobjects.class).getComments());
                    Log.d(TAG, testObj.getComments());
                    //);dataSnapshot.child(key).getValue(DBobjects.class);
                    //for (DataSnapshot newDs: ds.getChildren())
                    /*{
                        String myStr = newDs.getValue(String.class);
                        //DBobjects newObj =  newDs.getValue(DBobjects.class);
                        Log.d(TAG, key);
                    }*/
                }
                    //DBobjects testObj = new DBobjects();
                    //testObj.setComments(
                    //DataSnapshot newDs =
                    //DBobjects testObj = ds.child(key).getValue(DBobjects.class);
                    //String myStr = myObj.getComments();

                    //String key = newSnapshot.getKey();
                    //DatabaseReference newRef = myRef.child(key);
                    //Log.d(TAG, key);
                    //testObj = dataSnapshot.child(key).getValue(DBobjects.class);
                    //Log.d(TAG, "Comment: " + testObj.getComments());
                    //Log.d(TAG, myStr);
    }

    public void sendComment(View view) {
        Intent intent = new Intent(getApplicationContext(), Reaction.class);
        intent.putExtra("Bill", bill);
        startActivity(intent);

    }

}
