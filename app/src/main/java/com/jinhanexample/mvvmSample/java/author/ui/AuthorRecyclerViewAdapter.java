package com.jinhanexample.mvvmSample.java.author.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jinhanexample.R;
import com.jinhanexample.mvvmSample.java.author.data.AuthorEntity;
import com.jinhanexample.mvvmSample.java.author.listener.LongClickListener;

import java.util.ArrayList;
import java.util.List;

public class AuthorRecyclerViewAdapter extends RecyclerView.Adapter<AuthorRecyclerViewAdapter.ViewHolder> {

    private List<AuthorEntity> authorEntityList = new ArrayList<>();
    private LongClickListener longClickListener;

    public AuthorRecyclerViewAdapter(LongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(authorEntityList.get(position));
    }

    @Override
    public int getItemCount() {
        return authorEntityList.size();
    }

    public void addItem(List<AuthorEntity> authorEntityList) {

        this.authorEntityList = authorEntityList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView authorName = itemView.findViewById(R.id.authorName);
        TextView authorBooks = itemView.findViewById(R.id.authorBooks);
        TextView authorNation = itemView.findViewById(R.id.authorNation);

        void bind(AuthorEntity authorEntity) {
            authorName.setText(authorEntity.getName());
            authorBooks.setText(authorEntity.getBooks());
            authorNation.setText(authorEntity.getNation());

            itemView.setOnClickListener(view -> {
                longClickListener.onLongClickListener(authorEntity);
            });
        }
    }
}
