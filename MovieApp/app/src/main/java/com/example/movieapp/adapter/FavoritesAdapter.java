package com.example.movieapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<String> favoriteMovieNames;

    public FavoritesAdapter(List<String> favoriteMovieNames) {
        this.favoriteMovieNames = favoriteMovieNames;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        holder.textView.setText(favoriteMovieNames.get(position));
        holder.textView.setTextColor(Color.WHITE);  // Yazı rengini beyaz yap
    }

    @Override
    public int getItemCount() {
        return favoriteMovieNames.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
