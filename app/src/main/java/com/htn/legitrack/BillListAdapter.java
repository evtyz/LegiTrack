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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillListViewHolder> {
    List<Bill> billList = new ArrayList<>();
    RequestQueue queue;
    String state;

    public static final String APIKEY = "2a5c50a9-57e4-40e1-8f15-b53984537477";

    public static final int NUM_BILLS_PER_PAGE = 20;

    private int pageLoaded;

    private int num_bills_loaded;

    private ArrayList<String> subjects;


    BillListAdapter(Context context, String state, ArrayList<String> subjects) {
        queue = Volley.newRequestQueue(context);
        this.state = state;
        pageLoaded = 1;

        num_bills_loaded = 0;
        this.subjects = subjects;
        loadBills();
    }

    public void loadBills() {

        if (pageLoaded > 3) {
            return;
        }

        String url = "https://v3.openstates.org/bills?jurisdiction="
                .concat(state)
                .concat("&apikey=")
                .concat(APIKEY)
                .concat("&include=abstracts&include=sources")
                .concat("&per_page=20")
                .concat("&page=")
                .concat(Integer.toString(pageLoaded)); // TODO
        pageLoaded++;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray results = response.getJSONArray("results");
                if (results.length() == 0) {
                    return;
                }
                for (int i = 0; i < results.length(); i++) {
                    Bill bill = new Bill(results.getJSONObject(i));
                    if (compareTwoLists(subjects, bill.subjects) || subjects.size() == 0) {
                        billList.add(bill);
                        num_bills_loaded++;
                    }
                    // TODO: do something to the bill
                }

                if (num_bills_loaded < NUM_BILLS_PER_PAGE) {
                    loadBills();
                }
                notifyDataSetChanged();
                //                notifyItemRangeInserted(start, ITEMS_PER_PAGE);
            } catch (JSONException e) {
                Log.e("JSON", "Json error");
            }
        }, error -> Log.e("Volley", "Volley error"));
        queue.add(request);
    }

    public static boolean compareTwoLists(ArrayList<String> a, ArrayList<String> b) {
        for (String wordA : a) {
            for (String wordB : b) {
                if (wordA.equals(wordB)) {
                    return true;
                }
            }
        }
        return false;
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
