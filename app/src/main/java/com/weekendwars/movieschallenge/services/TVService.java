package com.weekendwars.movieschallenge.services;

import com.weekendwars.movieschallenge.dto.MoviePage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TVService {

    /**
     * Makes an API call for requesting popular movies
     *
     * @return the observable with data
     */
    @GET("/3/tv/popular")
    Observable<MoviePage> getPopularTvShows();


    /**
     * Makes an API call for requesting a TV show's similar topic shows
     *
     * @param id the show's id
     * @return the observable with data
     */
    @GET("/3/tv/{id}/similar")
    Observable<MoviePage> getSimilarTvShows(@Path("id") int id);
}
