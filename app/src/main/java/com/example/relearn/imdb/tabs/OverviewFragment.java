package com.example.relearn.imdb.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.relearn.imdb.R;
import com.example.relearn.imdb.model.Movie;

public class OverviewFragment extends Fragment {

    TextView overview, releasedate, rating;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.overview_tab,container, false);
        overview = (TextView) rootview.findViewById(R.id.overview);
        releasedate = (TextView) rootview.findViewById(R.id.release_date);
        rating = (TextView) rootview.findViewById(R.id.rating);

        fa = super.getActivity();
        Movie movie = fa.getIntent().getParcelableExtra("movie");

        overview.setText(movie.getOverview());
        releasedate.setText(movie.getRelease_date());
        String vote = String.valueOf(movie.getVote_average());
        rating.setText(vote);

        return rootview;
    }

}