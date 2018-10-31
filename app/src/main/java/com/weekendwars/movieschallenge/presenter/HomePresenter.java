package com.weekendwars.movieschallenge.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.models.MoviesModel;
import com.weekendwars.movieschallenge.view.HomeView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends AbstractPresenter<HomeView> {

    private static final String KEY_STATE = "key-state";
    private static final int REQUEST_DEBOUNCE = 500;
    private MoviePage mState;

    @Override
    public void attachView(@NonNull final HomeView view) {
        super.attachView(view);

        if (mState == null) {
            requestPopularTvShows(1);
        } else {
            getView().render(mState.results);
        }
    }

    private void requestPopularTvShows(final int page) {
        addDisposable(MoviesModel.INSTANCE.getPopularTvShows(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(REQUEST_DEBOUNCE, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<MoviePage>() {
                    @Override
                    public void accept(final MoviePage moviePage) {
                        updateState(moviePage);

                        if (page > 1) {
                            getView().renderNewPage(moviePage.results);
                        } else {

                            if (mState.results.isEmpty()) {
                                getView().showEmptyView();
                            } else {
                                getView().render(moviePage.results);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) {

                        // If it failed requesting a new page we display a different error view
                        if (page > 1) {
                            getView().showListErrorView();
                        } else {
                            getView().showErrorView();
                        }
                    }
                }));
    }

    /* default */ void updateState(final MoviePage moviePage) {
        if (moviePage.page > 1) {
            /*
                If we're loading a new page we need to store the whole list on state so we can restore
                it when Activity's destroyed.
             */
            final List<Movie> unifiedList = new ArrayList<>(mState.results);
            unifiedList.addAll(moviePage.results);
            mState = new MoviePage(moviePage.page, moviePage.totalResults, moviePage.totalPages, unifiedList);
        } else {
            mState = moviePage;
        }
    }

    @Override
    public void restoreState(@NonNull final Bundle savedState) {
        mState = savedState.getParcelable(KEY_STATE);
    }

    @NonNull
    @Override
    public Bundle getState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_STATE, mState);

        return bundle;
    }

    /**
     * Requests the next page of data
     */
    public void requestNewPage() {
        if (mState.page < mState.totalPages) {
            requestPopularTvShows(mState.page + 1);
        }
    }

    /**
     * Called for refreshing data
     */
    public void onRefresh() {
        requestPopularTvShows(1);
    }

    @Override
    public String toString() {
        return "HomePresenter{"
                + "mState=" + mState
                + '}';
    }

}
