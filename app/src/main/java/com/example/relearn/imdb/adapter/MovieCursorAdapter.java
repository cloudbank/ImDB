package com.example.relearn.imdb.adapter;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.relearn.imdb.sqlite.MoviesContract;

public class MovieCursorAdapter extends RecyclerView.Adapter<MovieCursorAdapter.MovieViewHolder> {

    private Cursor cursor;
    private Context context;

    public MovieCursorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MovieCursorAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieCursorAdapter.MovieViewHolder holder, int position) {

        int moviePosterIndex = cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_MOVIE_PATH);
        int movieTitleIndex = cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_TITLE);
        cursor.moveToPosition(position);

        String posterPath = "http://image.tmdb.org/t/p/w342";
        Glide.with(holder.itemView.getContext())
                .load(posterPath + cursor.getString(moviePosterIndex))
                .into(holder.movieImage);

        holder.movieTitle.setText(cursor.getString(movieTitleIndex));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                cursor.moveToPosition(clickedPosition);
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                String moviePath = cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_MOVIE_PATH));
                String originalTitle = cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_TITLE));
                String overview = cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_OVERVIEW));
                double release_date = Double.parseDouble(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_REL_DATE)));
                String userRating = cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_RATING));
                int movieId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteMovies.COLUMN_ID)));

                Movie movie = new Movie(moviePath, originalTitle, overview, release_date, userRating, movieId);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    /**
     * When data changes and a re-query occurs, this function swaps the old Cursor
     * with a newly updated Cursor (Cursor c) that is passed in.
     */
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (cursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = cursor;
        this.cursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        }

    }

}