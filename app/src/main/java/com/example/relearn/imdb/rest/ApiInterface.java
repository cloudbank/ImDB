package com.example.relearn.imdb.rest;

import com.example.relearn.imdb.model.MovieList;
import com.example.relearn.imdb.model.ReviewList;
import com.example.relearn.imdb.model.TrailerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String filters);

    @GET("/3/movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String filters);

    @GET("/3/movie/{id}/videos")
    Call<TrailerList> getVideos(
            @Path("id") int id,
            @Query("api_key") String filters);

    @GET("/3/movie/{id}/reviews")
    Call<ReviewList> getReviews(
            @Path("id") int id,
            @Query("api_key") String filters);

}