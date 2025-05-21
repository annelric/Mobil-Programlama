package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView movieImage;
    TextView movieName;
    View playButton;
    Button btnFavorite, btnWatchList;

    String mName, mImage, mId, mFileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movieImage = findViewById(R.id.movie_image);
        movieName = findViewById(R.id.movie_name);
        playButton = findViewById(R.id.play_button);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnWatchList = findViewById(R.id.btnWatchList);

        // ID alma işlemi
        mId = getIntent().getStringExtra("movieId");
        if (mId == null) {
            int mIdInt = getIntent().getIntExtra("movieId", -1);
            mId = String.valueOf(mIdInt);
        }

        if (mId == null || mId.equals("-1")) {
            Toast.makeText(this, "Invalid movie ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        mName = getIntent().getStringExtra("movieName");
        mImage = getIntent().getStringExtra("movieImageUrl");
        mFileUrl = getIntent().getStringExtra("movieFile");

        Glide.with(this).load(mImage).into(movieImage);
        movieName.setText(mName);

        // Start Over (play) butonu tıklama olayı
        playButton.setOnClickListener(v -> {
            if (mFileUrl != null && !mFileUrl.isEmpty()) {
                Intent intent = new Intent(MovieDetails.this, VideoPlayerActivity.class);
                intent.putExtra("url", mFileUrl);
                startActivity(intent);
            } else {
                Toast.makeText(MovieDetails.this, "Video URL bulunamadı", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        btnFavorite.setOnClickListener(v -> {
            editor.putBoolean(mId + "_favorite", true);
            editor.putString(mId + "_name", mName);
            editor.apply();
            Toast.makeText(this, "Added to Favorites ❤️", Toast.LENGTH_SHORT).show();
        });

        btnWatchList.setOnClickListener(v -> {
            editor.putBoolean(mId + "_watchlist", true);
            editor.putString(mId + "_name", mName);
            editor.apply();
            Toast.makeText(this, "Added to Watchlist 📃", Toast.LENGTH_SHORT).show();
        });
    }
}
