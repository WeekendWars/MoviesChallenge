package com.weekendwars.movieschallenge.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.MoviesAdapter;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.presenter.HomePresenter;
import com.weekendwars.movieschallenge.view.HomeView;

import java.util.List;

public class HomeActivity extends AbstractActivity<HomeView, HomePresenter> implements HomeView,
        MoviesAdapter.MovieActionListener {

    private static final int STATE_LIST = 1;
    private static final int STATE_EMPTY = 2;
    private static final int STATE_NETWORK = 3;
    private final MoviesAdapter mAdapter = new MoviesAdapter();
    private ViewFlipper mViewFlipper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewFlipper = findViewById(R.id.viewFlipper);

        final RecyclerView recyclerView = findViewById(R.id.moviesListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setMoviewActionListener(this);
    }

    @Override
    protected HomeView getMvpView() {
        return this;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    public void render(@NonNull final List<Movie> data) {
        mAdapter.setItems(data);
        mViewFlipper.setDisplayedChild(STATE_LIST);
    }

    @Override
    public void showProgress() {
        mAdapter.showProgress(true);
    }

    @Override
    public void showEmptyView() {
        mViewFlipper.setDisplayedChild(STATE_EMPTY);
    }

    @Override
    public void showErrorView() {
        mViewFlipper.setDisplayedChild(STATE_NETWORK);
    }

    @Override
    public void renderNewPage(@NonNull final List<Movie> data) {
        mAdapter.showProgress(false);
        mAdapter.addItems(data);
    }

    @Override
    public void onMovieSelected(@NonNull final Movie movie) {
        startActivity(MovieDetailActivity.getIntent(this, movie));
    }
}
