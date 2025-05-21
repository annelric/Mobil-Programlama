package com.example.movieapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder> {

    private List<String> watchlistMovieNames;

    public WatchlistAdapter(List<String> watchlistMovieNames) {
        this.watchlistMovieNames = watchlistMovieNames;
    }

    @NonNull
    @Override
    public WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new WatchlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistViewHolder holder, int position) {
        holder.textView.setText(watchlistMovieNames.get(position));
        holder.textView.setTextColor(Color.WHITE);  // Yazı rengini beyaz yap
    }

    @Override
    public int getItemCount() {
        return watchlistMovieNames.size();
    }

    static class WatchlistViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public WatchlistViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
