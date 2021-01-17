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

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListViewHolder> {
    List<Bill> billList = new ArrayList<>();
    RequestQueue queue;
    String state;

    public static final String APIKEY = "2a5c50a9-57e4-40e1-8f15-b53984537477";

    public static final int NUM_BILLS_PER_PAGE = 20;

    private int pageLoaded;

    private int numPagesLoaded;

    private String[] queryTerms;

    private int updateCounter = 0;

    private Set<String> IDsAlreadySeen = new HashSet<>();

    BillListAdapter(Context context, String state, String[] queryTerms) {
        queue = Volley.newRequestQueue(context);
        this.state = state;
        pageLoaded = 1;
        this.queryTerms = queryTerms;
        loadBills();
    }

    public void loadBills() {

        // Stop loading legislation after 3 pages.
        if (pageLoaded * queryTerms.length > 3 || pageLoaded > 3) {
            return;
        }

        if (queryTerms.length == 0) {
            String url = "https://v3.openstates.org/bills?jurisdiction="
                    .concat(state)
                    .concat("&apikey=")
                    .concat(APIKEY)
                    .concat("&include=abstracts&include=sources")
                    .concat("&per_page=20")
                    .concat("&page=")
                    .concat(Integer.toString(pageLoaded)); // TODO
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                try {
                    JSONArray results = response.getJSONArray("results");
                    if (results.length() == 0) {
                        return;
                    }
                    for (int i = 0; i < results.length(); i++) {
                        Bill bill = new Bill(results.getJSONObject(i));
                        billList.add(bill);
                        // TODO: do something to the bill
                    }
                    notifyDataSetChanged();

                    //                notifyItemRangeInserted(start, ITEMS_PER_PAGE);
                } catch (JSONException e) {
                    Log.e("JSON", "Json error");
                }
            }, error -> Log.e("Volley", "Volley error"));
            queue.add(request);
        } else {
            String urlbase = "https://v3.openstates.org/bills?jurisdiction="
                    .concat(state)
                    .concat("&apikey=")
                    .concat(APIKEY)
                    .concat("&include=abstracts&include=sources")
                    .concat("&per_page=20")
                    .concat("&page=")
                    .concat(Integer.toString(pageLoaded));
            ArrayList<Bill> addedBills = new ArrayList<>();
            for (String term : queryTerms) {
                String url = urlbase.concat("&q=").concat(term);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray results = response.getJSONArray("results");

                        updateCounter += 1;
                        if (results.length() == 0) {
                            return;
                        }
                        for (int i = 0; i < results.length(); i++) {
                            Bill bill = new Bill(results.getJSONObject(i));
                            addedBills.add(bill);
                        }

                        if (updateCounter % queryTerms.length == 0) {
                            Collections.sort(addedBills);
                            Collections.reverse(addedBills);
                            for (Bill bill : addedBills) {
                                if (IDsAlreadySeen.add(bill.id)) {
                                    billList.add(bill);
                                }
                            }

                            notifyDataSetChanged();
                        }

                        //                notifyItemRangeInserted(start, ITEMS_PER_PAGE);
                    } catch (JSONException e) {
                        Log.e("JSON", "Json error");
                    }
                }, error -> {
                    error.printStackTrace();
                    Log.e("Volley", "Volley error");
                });
                queue.add(request);
            }
        }

        pageLoaded++;



    }


    @NonNull
    @Override
    public BillListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_item, parent, false);
        return new BillListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillListViewHolder holder, int position) {
        Bill current = billList.get(position);
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
