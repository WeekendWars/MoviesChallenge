package com.weekendwars.movieschallenge.dto;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Movie {
    public final String name;
    public final int voteCount;
    public final String firstAirDate;
    public final String backdropPath;
    public final String posterPath;
    public final String overview;

    /**
     * Default constructor
     *
     * @param name          the movie's name
     * @param voteCount     the quantity of votes the movie's received
     * @param firstAirDate  the release date
     * @param backdropPath the backdrop image url
     * @param posterPath    the poster image url
     * @param overview      the moview's overview
     */
    public Movie(@NonNull final String name, final int voteCount, @NonNull final String firstAirDate,
                 @Nullable final String backdropPath, @Nullable final String posterPath,
                 @NonNull final String overview) {
        this.name = name;
        this.voteCount = voteCount;
        this.firstAirDate = firstAirDate;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "name='" + name + '\''
                + ", voteCount=" + voteCount
                + ", firstAirDate='" + firstAirDate + '\''
                + ", backgdropPath='" + backdropPath + '\''
                + ", posterPath='" + posterPath + '\''
                + ", overview='" + overview + '\''
                + '}';
    }
}
