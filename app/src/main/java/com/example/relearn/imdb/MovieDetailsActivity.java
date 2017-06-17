package com.example.relearn.imdb;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.relearn.imdb.adapter.PagerAdapter;
import com.example.relearn.imdb.sqlite.MoviesContract;
import com.example.relearn.imdb.sqlite.MoviesDBHelper;

public class MovieDetailsActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;
    private ImageView mMoviePoster;
    private int maxScrollSize;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private SQLiteDatabase database;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        setUpUIElements();
        setUpViewPagerTabs();

        MoviesDBHelper dbHelper = new MoviesDBHelper(this);
        database = dbHelper.getWritableDatabase();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }

    private void setUpViewPagerTabs() {

        //setup TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //add tabs
        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Trailers"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //setup ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        //bind PagerAdapter to viewPager
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    void setUpUIElements() {

        // Setup CollapsingToolbarLayout
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

        // Setup AppBarLayout
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(this);
        maxScrollSize = appBarLayout.getTotalScrollRange();

        //setup Toolbar and add onClick listener
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // setup Floating Action Button
        final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabs);
        if (sharedPreferences.getBoolean(movie.id, false)) {
            floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MovieDetailsActivity.this, R.drawable.ic_favorite_white_24px));
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sharedPreferences.getBoolean(movie.id, false)) {
                    // if true
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MovieDetailsActivity.this, R.drawable.ic_favorite_white_24px));
                    editor.putBoolean(movie.id, true);
                    editor.apply();

                    //Create new ContentValues object
                    ContentValues values = new ContentValues();

                    // Put the movie details into the contentValues object
                    values.put(MoviesContract.FavoriteMovies.COLUMN_ID, id);
                    values.put(MoviesContract.FavoriteMovies.COLUMN_TITLE, title);
                    values.put(MoviesContract.FavoriteMovies.COLUMN_MOVIE_PATH, moviePath);
                    values.put(MoviesContract.FavoriteMovies.COLUMN_OVERVIEW, overview);
                    values.put(MoviesContract.FavoriteMovies.COLUMN_REL_DATE, relDate);
                    values.put(MoviesContract.FavoriteMovies.COLUMN_RATING, rating);

                    // Insert the contentvalues via a ContentResolver
                    Uri uri = getContentResolver().insert(MoviesContract.FavoriteMovies.CONTENT_URI, values);

                    if (uri != null) {
                        Snackbar.make(collapsingToolbarLayout, "Added to Favourites", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    //false
                    floatingActionButton.setImageDrawable(ContextCompat.getDrawable(MovieDetailsActivity.this, R.drawable.ic_favorite_border_white_24px));
                    editor.putBoolean(movie.id, false);
                    editor.apply();

                    boolean success = removeFavourite(Long.parseLong(movie.id));

                    if (success) {
                        Snackbar.make(collapsingToolbarLayout, "Removed from Favourites", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (maxScrollSize == 0)
            maxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / maxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mMoviePoster.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(200)
                    .start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mMoviePoster.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

    private boolean removeFavourite(long id) {
        return database.delete(MoviesContract.FavoriteMovies.TABLE_NAME, MoviesContract.FavoriteMovies.COLUMN_ID + "=" + id, null) > 0;
    }

}
