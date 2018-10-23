package com.weekendwars.movieschallenge.view;

import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.views.AbstractView;
import com.weekendwars.movieschallenge.dto.Movie;

import java.util.List;

public interface HomeView extends AbstractView {

    /**
     * Called for rendering data
     *
     * @param data the list of movies being rendered
     */
    void render(@NonNull final List<Movie> data);

    /**
     * Called for displaying list's progress
     */
    void showProgress();

    /**
     * Called for displaying list's empty state
     */
    void showEmptyView();

    /**
     * Called for displaying the error view
     */
    void showErrorView();

    /**
     * Called for loading a new page on the list.
     *
     * @param data the data being attached to the list
     */
    void renderNewPage(@NonNull final List<Movie> data);
}
