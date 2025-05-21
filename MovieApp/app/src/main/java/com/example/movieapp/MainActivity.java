package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.movieapp.WatchlistActivity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.movieapp.adapter.BannerMoviesPagerAdapter;
import com.example.movieapp.adapter.MainRecyclerAdapter;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.BannerMovies;
import com.example.movieapp.model.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
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
    Timer sliderTimer;

    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    DrawerLayout drawerLayout;
    NavigationView navView;
    androidx.appcompat.widget.Toolbar toolbar; // AndroidX Toolbar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tanımlar
        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // Toolbar'ı destekleyici hale getir
        setSupportActionBar(toolbar);

        // Drawer Toggle (Menüyü açan buton vs.)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();

            if (id == R.id.nav_favorites) {
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.nav_watchlist) {
                startActivity(new Intent(MainActivity.this, WatchlistActivity.class));
                drawerLayout.closeDrawers();
                return true;
            }

            return false; // Menüde başka bir şey tıklanırsa
        });


        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "ATATÜRK", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/atat%C3%BCrk.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Atat%C3%BCrk+1881+-+1919+(1.+Film)+_+Fragman.mp4"));
        homeBannerList.add(new BannerMovies(2, "Incendies", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/incendies.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/_%C4%B0%C3%A7imdeki+Yang%C4%B1n+(2010)+-+T%C3%BCrk%C3%A7e+Altyaz%C4%B1l%C4%B1+1.+Fragman.mp4"));
        homeBannerList.add(new BannerMovies(3, "Inception", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/inception.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Inception+-+Ba%C5%9Flang%C4%B1%C3%A7+T%C3%BCrk%C3%A7e+Altyaz%C4%B1l%C4%B1+Fragman.mp4"));
        homeBannerList.add(new BannerMovies(4, "Old Boy", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/old_boy.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/_%C4%B0htiyar+Delikanl%C4%B1+(2003)+-+T%C3%BCrk%C3%A7e+Altyaz%C4%B1l%C4%B1+1.+Fragman.mp4"));
        homeBannerList.add(new BannerMovies(5, "Seven", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/seven.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeBannerList.add(new BannerMovies(6, "Sweeney Todd", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/sweeny_todd.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "Beyaz Show", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/Beyaz-show.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/20+May%C4%B1s+2016+Beyaz+Show+Fragman%C4%B1.mp4"));
        tvShowBannerList.add(new BannerMovies(2, "Güldür Güldür", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/g%C3%BCld%C3%BCr.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/G%C3%BCld%C3%BCr+G%C3%BCld%C3%BCr+Show+%E2%80%93+405.B%C3%B6l%C3%BCm+Tan%C4%B1t%C4%B1m%C4%B1.mp4"));
        tvShowBannerList.add(new BannerMovies(3, "İbrahim Selim İle Bu Gece", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/ibrahim_selim_3841c7f3f8.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        tvShowBannerList.add(new BannerMovies(4, "The Tonight Show Jimmy Fallon", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/jimmy.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        tvShowBannerList.add(new BannerMovies(5, "Katarsis", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/katarsis.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        tvShowBannerList.add(new BannerMovies(6, "Ricky Gervais", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/ricky.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "Charlie'nin Çikolata Fabrikası", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/%C3%A7ikolata.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Charlie'nin+%C3%87ikolata+Fabrikas%C4%B1+T%C3%BCrk%C3%A7e+Dublaj+Fragman.mp4"));
        movieBannerList.add(new BannerMovies(2, "Dune", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/dune.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Dune_%C3%87%C3%B6l+Gezegeni+_+T%C3%BCrk%C3%A7e+Altyaz%C4%B1l%C4%B1+Ana+Fragman+(1).mp4"));
        movieBannerList.add(new BannerMovies(3, "Harry Potter", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/harry_potter.webp", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Harry+Potter+and+the+Sorcerer's+Stone+(2001)+Official+Trailer+-+Daniel+Radcliffe+Movie+HD.mp4"));
        movieBannerList.add(new BannerMovies(4, "Interstellar", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/%C4%B1nterstellar.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        movieBannerList.add(new BannerMovies(5, "Amazing Spider Man", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/spider.webp", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        movieBannerList.add(new BannerMovies(6, "Star Wars", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/star.webp", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "Garfield", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/garfield.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Garfield+_+TR+Dublajl%C4%B1+Fragman+_+31+May%C4%B1s+2024.mp4"));
        kidsBannerList.add(new BannerMovies(2, "Toy Story", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/toy_story.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        kidsBannerList.add(new BannerMovies(3, "Lilo ve Stich", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/lilo.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Lilo+%26+Sti%C3%A7+_+Yeni+Resmi+Fragman+_+23+May%C4%B1s'ta+Sinemalarda!.mp4"));
        kidsBannerList.add(new BannerMovies(4, "Lion King", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/lion.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        kidsBannerList.add(new BannerMovies(5, "Hotel Transilvanya", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/otel.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        kidsBannerList.add(new BannerMovies(6, "Wall-e", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/wall_e.png", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        setBannerMoviesPagerAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(movieBannerList);
                        return;
                    case 3:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;
                    default:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(homeBannerList);
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });

        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "The Boys", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/watch+next/The+Boys.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/The+Boys+-+4.+Sezon+Resmi+Fragman.mp4"));
        homeCatListItem1.add(new CategoryItem(2, "La La Land", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/watch+next/La+la+land.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/La+La+Land+(2016+Movie)+Official+Trailer+%E2%80%93+'Dreamers'.mp4"));
        homeCatListItem1.add(new CategoryItem(3, "Mamma Mia", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/watch+next/mamma.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem1.add(new CategoryItem(4, "Lord of the Rings", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/watch+next/lotr.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem1.add(new CategoryItem(5, "Mad Max Fury Road", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/watch+next/mad+max.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1, "Aşk Tesadüfleri Sever", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/t%C3%BCrk/a%C5%9Fk.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/A%C5%9Fk+Tesad%C3%BCfleri+Sever+(Love+Just+A+Coincidence)+_+Fragman+(2011).mp4"));
        homeCatListItem2.add(new CategoryItem(2, "Babam ve Oğlum", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/t%C3%BCrk/babam.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem2.add(new CategoryItem(3, "Issız Adam", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/t%C3%BCrk/%C4%B1ss%C4%B1z.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem2.add(new CategoryItem(4, "Mucize", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/t%C3%BCrk/Mucize.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem2.add(new CategoryItem(5, "Selvi Boylum Al Yazmalım", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/t%C3%BCrk/Selvi+Boylum+Al+Yazmal%C4%B1m+1977.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new CategoryItem(1, "Home Alone", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/family/home+alone.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Home+Alone+(1990)+Trailer+%231+_+Movieclips+Classic+Trailers.mp4"));
        homeCatListItem3.add(new CategoryItem(2, "Monsters University", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/family/monster.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Sevimli+Canavarlar+%C3%9Cniversitesi+Yeni+T%C3%BCrk%C3%A7e+Fragman.mp4"));
        homeCatListItem3.add(new CategoryItem(3, "Narnia", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/family/narnia.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem3.add(new CategoryItem(4, "Finding Nemo", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/family/nemo.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem3.add(new CategoryItem(5, "Wall-e", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/family/wall+e.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem4.add(new CategoryItem(1, "1917", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/amazon/1917.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/1917+_+Altyaz%C4%B1l%C4%B1+Fragman.mp4"));
        homeCatListItem4.add(new CategoryItem(2, "Avatar", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/amazon/avatar.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Avatar_+Suyun+Yolu+_+Yeni+Dublajl%C4%B1+Resmi+Fragman+_.mp4"));
        homeCatListItem4.add(new CategoryItem(3, "Eternal Sunshine Of The Spotless Mind", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/amazon/Eternal+Sunshine+Of+The+Spotless+Mind+(2004).jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem4.add(new CategoryItem(4, "Rain Man", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/amazon/Rain+Man.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));
        homeCatListItem4.add(new CategoryItem(5, "Sherlock Holmes", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/movie_app_image/amazon/sherlock.jpg", "https://movieappsenaaksoy.s3.eu-north-1.amazonaws.com/video/Oyuncak+Hikayesi+4+_+Toy+Story+4++-+Fragman+(T%C3%BCrk%C3%A7e+Dublajl%C4%B1)+(1).mp4"));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Watch next TV and movies", homeCatListItem1));
        allCategoryList.add(new AllCategory(2, "Movies in Turk", homeCatListItem2));
        allCategoryList.add(new AllCategory(3, "Kids adn family movies", homeCatListItem3));
        allCategoryList.add(new AllCategory(4, "Amazon Original series", homeCatListItem4));

        setMainRecycler(allCategoryList);
    }

    private void setBannerMoviesPagerAdapter(List<BannerMovies> bannerMoviesList) {
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        if (sliderTimer != null) {
            sliderTimer.cancel();
        }
        sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 5000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }

    private void setMainRecycler(List<AllCategory> allCategoryList) {
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(allCategoryList, this);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    private void setScrollDefaultState() {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0, 0);
        appBarLayout.setExpanded(true);
    }

    class AutoSlider extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(() -> {
                if (bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() - 1) {
                    bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                } else {
                    bannerMoviesViewPager.setCurrentItem(0);
                }
            });
        }
    }
}
