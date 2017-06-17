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
import com.example.relearn.imdb.adapter.TrailerAdapter;
import com.example.relearn.imdb.model.Movie;
import com.example.relearn.imdb.model.TrailerList;
import com.example.relearn.imdb.rest.ApiClient;
import com.example.relearn.imdb.rest.ApiInterface;

public class TrailerFragment extends Fragment {

    private RecyclerView rRecyclerView;
    private ApiClient apiClient;
    private TrailerAdapter trailerAdapter;
    private FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.trailer_main,container, false);
        apiClient = new ApiClient();
        rRecyclerView = (RecyclerView) rootview.findViewById(R.id.trailer_recycler_view);
        rRecyclerView.setHasFixedSize(true);
        rRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        rRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        fa = super.getActivity();
        Movie movie = fa.getIntent().getParcelableExtra("movie");

        loadTrailerData(movie.getId());
        return rootview;

    }

    void loadTrailerData(int id) {

        String API_KEY = "e670cb047709fb74fb05ab7a751f3c08";
        ApiInterface apiInterface = apiClient.getService();
        Call<TrailerList> trailerListCall = apiInterface.getVideos(id, API_KEY);
        trailerListCall.enqueue(new Callback<TrailerList>() {
            @Override
            public void onResponse(Call<TrailerList> call, Response<TrailerList> response) {
                if (response.isSuccessful()) {
                    TrailerList trailerList = response.body();
                    trailerAdapter = new TrailerAdapter(trailerList.getResults());
                    rRecyclerView.setAdapter(trailerAdapter);
                } else {
                    Toast.makeText(getContext(), R.string.failedRequest, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrailerList> call, Throwable t) {
                Toast.makeText(getContext(), R.string.failedInternetRequest,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
