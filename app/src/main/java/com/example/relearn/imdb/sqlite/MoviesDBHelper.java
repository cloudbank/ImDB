package com.example.relearn.imdb.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MoviesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FavoriteMoviesDB.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " +
                MoviesContract.FavoriteMovies.TABLE_NAME + " (" +
                MoviesContract.FavoriteMovies.COLUMN_ID + " INTEGER PRIMARY KEY," +
                MoviesContract.FavoriteMovies.COLUMN_TITLE + " TEXT," +
                MoviesContract.FavoriteMovies.COLUMN_MOVIE_PATH + " TEXT," +
                MoviesContract.FavoriteMovies.COLUMN_OVERVIEW + " TEXT," +
                MoviesContract.FavoriteMovies.COLUMN_REL_DATE + " TEXT," +
                MoviesContract.FavoriteMovies.COLUMN_RATING + " TEXT)";
        db.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MoviesContract.FavoriteMovies.TABLE_NAME);
        onCreate(db);
    }

}