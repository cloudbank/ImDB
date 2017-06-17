package com.example.relearn.imdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("vote_average")
    @Expose
    private double vote_average;
    @SerializedName("id")
    @Expose
    private int id;

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(backdrop_path);
        parcel.writeString(original_title);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeDouble(vote_average);
        parcel.writeInt(id);
    }

    protected Movie(Parcel in) {
        backdrop_path = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readDouble();
        id = in.readInt();
    }

    public Movie(String backdrop_path, String originalTitle, String overview, double vote_average, String release_date, int id) {
        this.backdrop_path = backdrop_path;
        this.original_title = originalTitle;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.id = id;
    }

    public Movie() {

    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}