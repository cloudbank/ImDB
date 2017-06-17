package com.example.relearn.imdb.sqlite;

import android.net.Uri;
import android.provider.BaseColumns;

public class MoviesContract {

    public static final String AUTHORITY = "com.example.relearn.imdb";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String MOVIES_PATH = "movies";

    public static final class FavoriteMovies implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(MOVIES_PATH).build();

        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_ID = "movieId";
        public static final String COLUMN_MOVIE_PATH = "moviePosterPath";
        public static final String COLUMN_TITLE = "movieTitle";
        public static final String COLUMN_OVERVIEW = "movieOverview";
        public static final String COLUMN_REL_DATE = "movieReleaseDate";
        public static final String COLUMN_RATING = "movieUserRating";

    }

}
