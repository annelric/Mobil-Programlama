package com.example.movieapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private ExoPlayer exoPlayer;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tam ekran yapmak için
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
                android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_player);

        playerView = findViewById(R.id.playerView);
        playerView.setUseController(true);
        playerView.setControllerShowTimeoutMs(0);
        playerView.showController();

        String url = getIntent().getStringExtra("url");

        if (url == null || url.isEmpty()) {
            Toast.makeText(this, "Video URL bulunamadı!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        android.util.Log.d("VideoPlayerActivity", "Oynatılan video URL: " + url);

        exoPlayer = new ExoPlayer.Builder(this).build();

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));

        exoPlayer.setMediaItem(mediaItem);
        playerView.setPlayer(exoPlayer);
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
