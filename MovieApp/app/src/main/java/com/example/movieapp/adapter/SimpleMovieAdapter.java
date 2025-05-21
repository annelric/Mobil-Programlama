package com.example.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;

import java.util.List;

public class SimpleMovieAdapter extends RecyclerView.Adapter<SimpleMovieAdapter.MovieViewHolder> {

    private Context context;
    private List<String> movieIds;

    public SimpleMovieAdapter(Context context, List<String> movieIds) {
        this.context = context;
        this.movieIds = movieIds;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        String movieId = movieIds.get(position);
        holder.movieTitle.setText("Movie ID: " + movieId);
    }

    @Override
    public int getItemCount() {
        return movieIds.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.simple_movie_title);
        }
    }
}
