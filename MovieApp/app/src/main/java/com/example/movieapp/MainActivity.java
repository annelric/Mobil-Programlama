package com.example.movieapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.movieapp.adapter.BannerMoviesPagerAdapter;
import com.example.movieapp.model.BannerMovies;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById((R.id.tabLayout));

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "ATATÜRK", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/atat%C3%BCrk.jpg", ""));
        homeBannerList.add(new BannerMovies(2, "Incendies", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/incendies.jpg", ""));
        homeBannerList.add(new BannerMovies(3, "Inception", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/inception.jpg", ""));
        homeBannerList.add(new BannerMovies(4, "Old Boy", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/old_boy.jpg", ""));
        homeBannerList.add(new BannerMovies(5, "Seven", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/seven.jpg", ""));
        homeBannerList.add(new BannerMovies(6, "Sweeney Todd", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/sweeny_todd.jpg", ""));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "Beyaz Show", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/Beyaz-show.jpg", ""));
        tvShowBannerList.add(new BannerMovies(2, "Güldür Güldür", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/g%C3%BCld%C3%BCr.png", ""));
        tvShowBannerList.add(new BannerMovies(3, "İbrahim Selim İle Bu Gece", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/ibrahim_selim_3841c7f3f8.jpg", ""));
        tvShowBannerList.add(new BannerMovies(4, "The Tonight Show Jimmy Fallon", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/jimmy.jpg", ""));
        tvShowBannerList.add(new BannerMovies(5, "Katarsis", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/katarsis.jpg", ""));
        tvShowBannerList.add(new BannerMovies(6, "Ricky Gervais", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/ricky.png", ""));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "Charlie'nin Çikolata Fabrikası", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/%C3%A7ikolata.jpg", ""));
        movieBannerList.add(new BannerMovies(2, "Dune", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/dune.jpg", ""));
        movieBannerList.add(new BannerMovies(3, "Harry Potter", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/harry_potter.webp", ""));
        movieBannerList.add(new BannerMovies(4, "Interstellar", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/%C4%B1nterstellar.jpg", ""));
        movieBannerList.add(new BannerMovies(5, "Amazing Spider Man", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/spider.webp", ""));
        movieBannerList.add(new BannerMovies(6, "Star Wars", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/star.webp", ""));



        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "Garfield", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/garfield.png", ""));
        kidsBannerList.add(new BannerMovies(2, "Toy Story", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/toy_story.png", ""));
        kidsBannerList.add(new BannerMovies(3, "Lilo ve Stich", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/lilo.png", ""));
        kidsBannerList.add(new BannerMovies(4, "Lion King", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/lion.png", ""));
        kidsBannerList.add(new BannerMovies(5, "Hotel Transilvanya", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/otel.png", ""));
        kidsBannerList.add(new BannerMovies(6, "Wall-e", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/wall_e.png", ""));


        setBannerMoviesPagerAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                switch (tab.getPosition()) {
                    case 1:
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setBannerMoviesPagerAdapter(movieBannerList);
                        return;
                    case 3:
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;
                    default:
                        setBannerMoviesPagerAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setBannerMoviesPagerAdapter(List<BannerMovies> bannerMoviesList) {
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);  // ViewPager2 kullan
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 5000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }


    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() - 1) {
                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                    } else {
                        bannerMoviesViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}