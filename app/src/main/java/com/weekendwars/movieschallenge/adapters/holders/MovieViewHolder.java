package com.weekendwars.movieschallenge.adapters.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weekendwars.movieschallenge.dto.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    /**
     * Default constructor
     *
     * @param itemView the view being attached
     */
    public MovieViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    /**
     * Called on holder's binding
     *
     * @param movie the data object being attached to the view
     */
    public void onBind(@NonNull final Movie movie) {
        // TODO: Render data
    }
}
