package com.weekendwars.movieschallenge.view;

import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.views.AbstractView;
import com.weekendwars.movieschallenge.dto.Movie;

import java.util.List;

public interface MovieDetailView extends AbstractView {
    /**
     * Called for rendering data
     *
     * @param data the data being rendered
     */
    void render(@NonNull List<Movie> data);

    /**
     * Called on API error
     */
    void onError();
}
