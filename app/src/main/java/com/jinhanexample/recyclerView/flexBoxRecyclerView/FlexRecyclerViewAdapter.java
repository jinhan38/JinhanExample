package com.jinhanexample.recyclerView.flexBoxRecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.jinhanexample.R;

import java.util.ArrayList;

public class FlexRecyclerViewAdapter extends RecyclerView.Adapter<FlexItemViewHolder> {

    private static final String TAG = "FlexRecyclerViewAdapter";
    private ArrayList<String> dataList = new ArrayList<>();

    public FlexRecyclerViewAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FlexItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flex_recyclerview_item, parent, false);
        FlexItemViewHolder flexItemViewHolder = new FlexItemViewHolder(view);
        return flexItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlexItemViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount : " + dataList.size());
        return dataList.size();
    }
}
