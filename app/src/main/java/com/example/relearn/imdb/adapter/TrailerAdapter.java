package com.example.relearn.imdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.relearn.imdb.R;
import com.example.relearn.imdb.model.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailersViewHolder> {

    private List<Trailer> trailerList;

    public TrailerAdapter(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }

    @Override
    public TrailersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_card, parent, false);
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TrailersViewHolder holder, int position) {

        final Trailer currentTrailer = trailerList.get(position);

        holder.trailerName.setText(currentTrailer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + currentTrailer.getKey()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    class TrailersViewHolder extends RecyclerView.ViewHolder {

        private TextView trailerName;

        public TrailersViewHolder(View itemView) {
            super(itemView);
            trailerName = (TextView) itemView.findViewById(R.id.trailerName);
        }

    }

}