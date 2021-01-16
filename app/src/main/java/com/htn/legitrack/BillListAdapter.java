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

    private int pageLoaded;


    BillListAdapter(Context context, String state) {
        queue = Volley.newRequestQueue(context);
        this.state = state;
        pageLoaded = 1;
        loadBills();

    }

    public void loadBills() {

        String url = "https://v3.openstates.org/bills?jurisdiction="
                .concat(state)
                .concat("&apikey=9341f3e4-6ae0-4d2d-b498-9f1d9c0ff8a6")
                .concat("&include=abstracts&include=sources")
                .concat("&per_page=20")
                .concat("&page=")
                .concat(Integer.toString(pageLoaded)); // TODO
        pageLoaded++;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    addToList(results.getJSONObject(i));

                    // TODO: do something to the bill
                }
                notifyDataSetChanged();
                //                notifyItemRangeInserted(start, ITEMS_PER_PAGE);
            } catch (JSONException e) {
                Log.e("JSON", "Json error");
            }
        }, error -> Log.e("Volley", "Volley error"));
        queue.add(request);
    }

    public void addToList(JSONObject bill) {
        billList.add(new Bill(bill));
        Log.d("add", "add");
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
            billTextView = itemView.findViewById(R.id.textView7);
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
