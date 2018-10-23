package com.weekendwars.movieschallenge.services;

import com.weekendwars.movieschallenge.dto.MoviePage;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MoviesService {

    /**
     * Makes an API call for requesting popular movies
     *
     * @return the observable with data
     */
    @GET("/3/tv/popular")
    Observable<MoviePage> getMovies();
}
