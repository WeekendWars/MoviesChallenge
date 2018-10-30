package com.weekendwars.movieschallenge.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.models.MoviesModel;
import com.weekendwars.movieschallenge.view.MovieDetailView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresenter extends AbstractPresenter<MovieDetailView> {

    private static final String EXTRA_STATE = "extra-state";
    private final Movie mMovie;
    /* default */ MoviePage mState;

    /**
     * Default constructor
     *
     * @param movie the main movie's topic
     */
    public MovieDetailPresenter(@NonNull final Movie movie) {
        mMovie = movie;
    }

    @Override
    public void attachView(@NonNull final MovieDetailView view) {
        super.attachView(view);

        // If there's an state already we restore it
        if (mState == null) {
            requestSimilarTvShows();
        } else {
            view.render(mState.results);
        }
    }

    private void requestSimilarTvShows() {
        addDisposable(MoviesModel.INSTANCE.getSimilarTvShows(mMovie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Consumer<MoviePage>() {
                    @Override
                    public void accept(final MoviePage moviePage) {
                        mState = moviePage;

                        getView().render(moviePage.results);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) {
                        getView().onError();
                    }
                }));
    }

    @NonNull
    @Override
    public Bundle getState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_STATE, mState);

        return bundle;
    }

    @Override
    public void restoreState(@NonNull final Bundle savedState) {
        mState = savedState.getParcelable(EXTRA_STATE);
    }

    @Override
    public String toString() {
        return "MovieDetailPresenter{"
                + "mMovie=" + mMovie
                + ", mState=" + mState
                + '}';
    }
}
