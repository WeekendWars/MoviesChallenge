package com.weekendwars.movieschallenge.adapters.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.utils.PictureUtils;

public class SimilarTvShowHolder extends RecyclerView.ViewHolder {

    private final ImageView mImageView;
    private final TextView mTitleView;

    /**
     * Default constructor
     *
     * @param itemView the holder's view
     */
    public SimilarTvShowHolder(@NonNull final View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.coverView);
        mTitleView = itemView.findViewById(R.id.titleView);
    }

    /**
     * Called on holder's binding
     *
     * @param movie the bound data
     */
    public void onBind(final Movie movie) {
        PictureUtils.loadImage(mImageView, movie.backdropPath);
        mTitleView.setText(movie.name);
    }
}
