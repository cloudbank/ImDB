package com.example.relearn.imdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.relearn.imdb.MovieDetailsActivity;
import com.example.relearn.imdb.R;
import com.example.relearn.imdb.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private List<Movie> movieList;

    public MoviesAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {

        final Movie currentMovie = movieList.get(position);
        String posterPath = "http://image.tmdb.org/t/p/w342";

        Glide.with(holder.itemView.getContext())
                .load(posterPath + currentMovie.getBackdrop_path())
                .into(holder.movieImage);

        holder.movieTitle.setText(currentMovie.getOriginal_title());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", currentMovie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieTitle;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        }

    }

}