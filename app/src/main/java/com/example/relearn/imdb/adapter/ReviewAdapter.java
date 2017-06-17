package com.example.relearn.imdb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.relearn.imdb.R;
import com.example.relearn.imdb.model.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewsViewHolder> {

    private List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_card, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReviewsViewHolder holder, int position) {
        final Review currentReview = reviewList.get(position);

        holder.authorName.setText(currentReview.getAuthor());
        holder.contentText.setText(currentReview.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private TextView authorName, contentText;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            authorName = (TextView) itemView.findViewById(R.id.author);
            contentText = (TextView) itemView.findViewById(R.id.content);
        }

    }

}