package com.example.relearn.imdb.sqlite;

import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

    /*public List<FavMovie> getDataFromDB() {
        List<FavMovie> modelList = new ArrayList<FavMovie>();
        String query = "select * from " + FavoriteMoviesContract.FavoriteMovies.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                FavMovie favMovie = new FavMovie();

                favMovie.setOriginal_title(cursor.getString(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_TITLE)));
                favMovie.setBackdrop_path(cursor.getString(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_MOVIE_PATH)));
                favMovie.setId(cursor.getInt(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_TITLE)));
                favMovie.setRelease_date(cursor.getString(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_REL_DATE)));
                favMovie.setOverview(cursor.getString(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_OVERVIEW)));
                favMovie.setVote_average(cursor.getDouble(cursor.getColumnIndex(FavoriteMoviesContract.FavoriteMovies.COLUMN_RATING)));

                modelList.add(favMovie);
            } while (cursor.moveToNext());
        }
        Log.d("Movie data", modelList.toString());

        return modelList;
    }*/

}
