package com.weekendwars.movieschallenge.services;

import com.weekendwars.movieschallenge.dto.MoviePage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVService {

    /**
     * Makes an API call for requesting popular movies
     *
     * @param page the page being requested
     * @return the observable with data
     */
    @GET("/3/tv/popular")
    Observable<MoviePage> getPopularTvShows(@Query("page") int page);


    /**
     * Makes an API call for requesting a TV show's similar topic shows
     *
     * @param id the show's id
     * @return the observable with data
     */
    @GET("/3/tv/{id}/similar")
    Observable<MoviePage> getSimilarTvShows(@Path("id") int id);
}
