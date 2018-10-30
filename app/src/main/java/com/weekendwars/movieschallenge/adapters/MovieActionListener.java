package com.weekendwars.movieschallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.weekendwars.movieschallenge.dto.Movie;

public interface MovieActionListener {
    /**
     * Called when a movie's been selected by user's interaction
     *
     * @param holder the selected view holder
     * @param movie  the selected moview
     */
    void onMovieSelected(@NonNull RecyclerView.ViewHolder holder, @NonNull Movie movie);
}