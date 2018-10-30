package com.weekendwars.movieschallenge.models;

import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.networking.RetrofitAdapter;
import com.weekendwars.movieschallenge.services.TVService;

import io.reactivex.Observable;

public final class MoviesModel {

    public static final MoviesModel INSTANCE = new MoviesModel();

    private MoviesModel() {

    }

    /**
     * Makes an API call for requesting the movies pagination data
     *
     * @return the observable with data
     */
    public Observable<MoviePage> getPopularTvShows() {
        return getService().getPopularTvShows();
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
