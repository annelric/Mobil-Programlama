package com.example.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.FavoritesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favoriteRecyclerView;
    FavoritesAdapter favoritesAdapter;
    List<String> favoriteMovieNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // RecyclerView'u bul
        favoriteRecyclerView = findViewById(R.id.favoritesRecyclerView);

        // Favori film isimlerini listeye doldur
        favoriteMovieNames = new ArrayList<>();

        // Örnek: SharedPreferences'tan favorileri çek (senin kodunda zaten var)
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.endsWith("_favorite") && value instanceof Boolean && (Boolean) value) {
                String movieId = key.replace("_favorite", "");
                String movieName = prefs.getString(movieId + "_name", null);
                if (movieName != null) {
                    favoriteMovieNames.add(movieName);
                }
            }
        }

        // Adapteri oluştur ve RecyclerView’a bağla
        favoritesAdapter = new FavoritesAdapter(favoriteMovieNames);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoriteRecyclerView.setAdapter(favoritesAdapter);

        // Eğer listen değişirse adaptera bildir (genellikle gerekmez)
        favoritesAdapter.notifyDataSetChanged();
    }
}
