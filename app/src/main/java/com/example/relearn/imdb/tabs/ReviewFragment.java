package com.example.relearn.imdb.tabs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.relearn.imdb.R;
import com.example.relearn.imdb.adapter.ReviewAdapter;
import com.example.relearn.imdb.model.Movie;
import com.example.relearn.imdb.model.ReviewList;
import com.example.relearn.imdb.rest.ApiClient;
import com.example.relearn.imdb.rest.ApiInterface;

public class ReviewFragment extends Fragment {

    private RecyclerView rRecyclerView;
    private ApiClient apiClient;
    private ReviewAdapter reviewAdapter;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.reviews_main,container, false);

        apiClient = new ApiClient();
        rRecyclerView = (RecyclerView) rootview.findViewById(R.id.review_recycler_view);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        fa = super.getActivity();
        Movie movie = fa.getIntent().getParcelableExtra("movie");

        loadReviewData(movie.getId());

        return rootview;
    }

    void loadReviewData(int id) {

        String API_KEY = "e670cb047709fb74fb05ab7a751f3c08";
        ApiInterface apiInterface = apiClient.getService();
        Call<ReviewList> reviewListCall = apiInterface.getReviews(id, API_KEY);
        reviewListCall.enqueue(new Callback<ReviewList>() {
            @Override
            public void onResponse(Call<ReviewList> call, Response<ReviewList> response) {
                if (response.isSuccessful()) {
                    ReviewList reviewList = response.body();
                    reviewAdapter = new ReviewAdapter(reviewList.getResults());
                    rRecyclerView.setAdapter(reviewAdapter);
                } else {
                    Toast.makeText(getContext(), R.string.failedRequest, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReviewList> call, Throwable t) {
                Toast.makeText(getContext(), R.string.failedInternetRequest,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
