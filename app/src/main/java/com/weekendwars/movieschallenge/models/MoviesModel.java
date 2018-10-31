package com.weekendwars.movieschallenge.models;

import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.networking.RetrofitAdapter;
import com.weekendwars.movieschallenge.services.TVService;

import io.reactivex.Observable;

/**
 * This is the model class. Intended to handle any business logic. Since there's no business logic
 * on this project like "ordering a list of movies" It's only working as a gateway between the service
 * and the presenter
 */
public final class MoviesModel {

    public static final MoviesModel INSTANCE = new MoviesModel();

    private MoviesModel() {

    }

    /**
     * Makes an API call for requesting the movies pagination data
     *
     * @param page the offset page
     * @return the observable with data
     */
    public Observable<MoviePage> getPopularTvShows(final int page) {
        return getService().getPopularTvShows(page);
    }

    /**
     * Makes an API call for requesting a TV show's similar topic shows
     *
     * @param id the show's id
     * @return the observable with data
     */
    public Observable<MoviePage> getSimilarTvShows(final int id) {
        return getService().getSimilarTvShows(id);
    }

    private TVService getService() {
        return RetrofitAdapter.INSTANCE.retrofit.create(TVService.class);
    }
}
