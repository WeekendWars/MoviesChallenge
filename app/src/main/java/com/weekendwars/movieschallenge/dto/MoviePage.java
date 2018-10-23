package com.weekendwars.movieschallenge.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class MoviePage implements Parcelable {
    public static final Creator<MoviePage> CREATOR = new Creator<MoviePage>() {
        @Override
        public MoviePage createFromParcel(final Parcel in) {
            return new MoviePage(in);
        }

        @SuppressFBWarnings(value = "SUA_SUSPICIOUS_UNINITIALIZED_ARRAY",
                justification = "Parcelable contract")
        @Override
        public MoviePage[] newArray(final int size) {
            return new MoviePage[size];
        }
    };

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

    protected MoviePage(final Parcel in) {
        page = in.readInt();
        totalResults = in.readInt();
        totalPages = in.readInt();
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(page);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
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
