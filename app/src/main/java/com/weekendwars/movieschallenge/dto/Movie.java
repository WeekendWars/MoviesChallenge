package com.weekendwars.movieschallenge.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(final Parcel in) {
            return new Movie(in);
        }

        @SuppressFBWarnings(value = "SUA_SUSPICIOUS_UNINITIALIZED_ARRAY",
                justification = "Parcelable contract")
        @Override
        public Movie[] newArray(final int size) {
            return new Movie[size];
        }
    };

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

    protected Movie(final Parcel in) {
        name = in.readString();
        voteCount = in.readInt();
        firstAirDate = in.readString();
        backdropPath = in.readString();
        posterPath = in.readString();
        overview = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(name);
        parcel.writeInt(voteCount);
        parcel.writeString(firstAirDate);
        parcel.writeString(backdropPath);
        parcel.writeString(posterPath);
        parcel.writeString(overview);
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
