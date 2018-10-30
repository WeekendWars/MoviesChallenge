package com.weekendwars.movieschallenge.models;

import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.networking.RetrofitAdapter;
import com.weekendwars.movieschallenge.services.MoviesService;

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
    public Observable<MoviePage> getMovies() {
        return getService().getMovies();
    }

    private MoviesService getService() {
        return RetrofitAdapter.INSTANCE.retrofit.create(MoviesService.class);
    }
}
