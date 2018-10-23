package com.weekendwars.movieschallenge.dto;

import java.util.List;

public class MoviePage {
    public final int page;
    public final int totalResults;
    public final int totalPages;
    public final List<Movie> results;

    /**
     * Default constructor
     *
     * @param page         the current page according to the pagination position
     * @param totalResults the total quantity of results
     * @param totalPages   the total quantity of pages
     * @param results      the list of movies
     */
    public MoviePage(final int page, final int totalResults, final int totalPages,
                     final List<Movie> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    @Override
    public String toString() {
        return "MoviePage{"
                + "page=" + page
                + ", totalResults=" + totalResults
                + ", totalPages=" + totalPages
                + ", results=" + results
                + '}';
    }
}
