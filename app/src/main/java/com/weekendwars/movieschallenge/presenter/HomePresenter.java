package com.weekendwars.movieschallenge.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.movieschallenge.dto.MoviePage;
import com.weekendwars.movieschallenge.models.MoviesModel;
import com.weekendwars.movieschallenge.view.HomeView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends AbstractPresenter<HomeView> {

    private static final String KEY_STATE = "key-state";
    private MoviePage mState;

    @Override
    public void attachView(@NonNull final HomeView view) {
        super.attachView(view);

        if (mState == null) {
            requestMovies();
        } else {
            getView().render(mState.results);
        }
    }

    private void requestMovies() {
        addDisposable(MoviesModel.INSTANCE.getMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MoviePage>() {
                    @Override
                    public void accept(final MoviePage moviePage) {
                        mState = moviePage;

                        if (mState.results.isEmpty()) {
                            getView().showEmptyView();
                        } else {
                            getView().render(moviePage.results);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) {
                        getView().showErrorView();
                    }
                }));
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

    @Override
    public String toString() {
        return "HomePresenter{"
                + "mState=" + mState
                + '}';
    }
}
