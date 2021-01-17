package com.htn.legitrack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommListAdapter extends RecyclerView.Adapter<CommListAdapter.CommListViewHolder> {
    List<String> commentList = new ArrayList<>();
    RequestQueue queue;
    String comment;

    //public static final String APIKEY = "2a5c50a9-57e4-40e1-8f15-b53984537477";

    public static final int NUM_BILLS_PER_PAGE = 20;
    private int pageLoaded;
    private int numPagesLoaded;
    //private String[] queryTerms;
    private int updateCounter = 0;
    private Set<String> IDsAlreadySeen = new HashSet<>();

    CommListAdapter(Context context, String comment, String[] queryTerms) {
        queue = Volley.newRequestQueue(context);
        this.state = state;
        pageLoaded = 1;
        this.queryTerms = queryTerms;
        loadBills();
    }

    public void loadComms() {

        // Stop loading legislation after 3 pages.
        if (pageLoaded * queryTerms.length > 3 || pageLoaded > 3) {
            return;
        }


        } else {

                };

        pageLoaded++;



    }


    @NonNull
    @Override
    public CommListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_item, parent, false);
        return new CommListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommListViewHolder holder, int position) {
        S65iny current = commentList.get(position);
        holder.billTextView.setText(current.title);
        holder.billLayoutView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public static class BillListViewHolder extends RecyclerView.ViewHolder {
        public TextView billTextView;
        public LinearLayout billLayoutView;

        public BillListViewHolder(@NonNull View itemView) {
            super(itemView);
            billTextView = itemView.findViewById(R.id.bill_item_text);
            billLayoutView = itemView.findViewById(R.id.bill_item);

            billLayoutView.setOnClickListener(view -> {
                Bill current = (Bill) billLayoutView.getTag();
                Intent intent = new Intent(view.getContext(), BillInfo.class);
                intent.putExtra("bill", current);
                view.getContext().startActivity(intent);
            });
        }
    }
}
