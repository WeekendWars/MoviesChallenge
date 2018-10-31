package com.weekendwars.movieschallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.MovieActionListener;
import com.weekendwars.movieschallenge.adapters.SimilarTvShowAdapter;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.presenter.MovieDetailPresenter;
import com.weekendwars.movieschallenge.utils.FeedbackUtils;
import com.weekendwars.movieschallenge.utils.PictureUtils;
import com.weekendwars.movieschallenge.view.MovieDetailView;

import java.util.List;

public class MovieDetailActivity extends AbstractActivity<MovieDetailView, MovieDetailPresenter>
        implements MovieDetailView, MovieActionListener {

    private static final int STATE_LIST = 1;
    private static final String EXTRA_MOVIE = "extra-movie";
    private final SimilarTvShowAdapter mAdapter = new SimilarTvShowAdapter();
    private ViewFlipper mViewFlipper;
    private Movie mMovie;

    /**
     * Factory method
     *
     * @param context the application's context
     * @param movie   the movie's data being displayed
     * @return the intent with necessary data
     */
    public static Intent getIntent(@NonNull final Context context, @NonNull final Movie movie) {
        final Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mViewFlipper = findViewById(R.id.viewFlipper);
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        final ImageView coverView = findViewById(R.id.coverView);
        final TextView descriptionView = findViewById(R.id.descriptionView);
        final RecyclerView recyclerView = findViewById(R.id.similarShowsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);

        mAdapter.setMoviewActionListener(this);
        PictureUtils.loadImage(coverView, mMovie.backdropPath);
        collapsingToolbarLayout.setTitle(mMovie.name);
        descriptionView.setText(mMovie.overview);
    }

    @Override
    protected MovieDetailView getMvpView() {
        return this;
    }

    @Override
    protected MovieDetailPresenter createPresenter() {
        // If factory method has not been used we throw an exception
        if (getIntent().getExtras() == null || !getIntent().getExtras().containsKey(EXTRA_MOVIE)) {
            throw new AssertionError("Invalid activity invocation."
                    + "Please use it's factory method MovieActivity.getIntent()");
        }

        mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        return new MovieDetailPresenter(mMovie);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        // We do this so on back pressed we go back.
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void render(@NonNull final List<Movie> data) {
        mAdapter.setData(data);
        mViewFlipper.setDisplayedChild(STATE_LIST);
    }

    @Override
    public void onError() {
        FeedbackUtils.showErrorSnackbar(findViewById(R.id.rootView), getString(R.string.error_tv_show));
    }

    @Override
    public void onMovieSelected(@NonNull final RecyclerView.ViewHolder holder,
                                @NonNull final Movie movie) {
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
