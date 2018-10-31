package com.weekendwars.movieschallenge.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.MovieActionListener;
import com.weekendwars.movieschallenge.adapters.MoviesAdapter;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.presenter.HomePresenter;
import com.weekendwars.movieschallenge.view.HomeView;

import java.util.List;

public class HomeActivity extends AbstractActivity<HomeView, HomePresenter> implements HomeView,
        MovieActionListener {

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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView,
                                   final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!mAdapter.isLoading() && layoutManager.findLastVisibleItemPosition()
                        == mAdapter.getItemCount() - 1) {
                    getPresenter().requestNewPage();
                }
            }
        });

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
        setState(STATE_LIST);
    }

    @Override
    public void showProgress() {
        mAdapter.showProgress(true);
    }

    @Override
    public void showEmptyView() {
        setState(STATE_EMPTY);
    }

    @Override
    public void showErrorView() {
        setState(STATE_NETWORK);
    }

    private void setState(final int state) {
        if (state == mViewFlipper.getDisplayedChild()) {
            return;
        }

        mViewFlipper.setDisplayedChild(state);
    }

    @Override
    public void renderNewPage(@NonNull final List<Movie> data) {
        mAdapter.showProgress(false);
        mAdapter.addItems(data);
    }

    @Override
    public void onMovieSelected(@NonNull final RecyclerView.ViewHolder holder, @NonNull final Movie movie) {
        final Intent intent = MovieDetailActivity.getIntent(this, movie);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    holder.itemView.findViewById(R.id.coverView), "coverView");

            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
