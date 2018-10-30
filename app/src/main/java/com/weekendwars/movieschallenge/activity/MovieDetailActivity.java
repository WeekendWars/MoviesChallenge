package com.weekendwars.movieschallenge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.dto.Movie;
import com.weekendwars.movieschallenge.utils.PictureUtils;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "extra-movie";

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

        // If factory method has not been used we throw an exception
        if (getIntent().getExtras() == null || !getIntent().getExtras().containsKey(EXTRA_MOVIE)) {
            throw new AssertionError("Invalid activity invocation." +
                    "Please use it's factory method MovieActivity.getIntent()");
        }

        setContentView(R.layout.activity_movie_detail);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        render((Movie) getIntent().getParcelableExtra(EXTRA_MOVIE));
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

    private void render(final Movie movie) {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        final ImageView coverView = findViewById(R.id.coverView);
        final TextView descriptionView = findViewById(R.id.descriptionView);

        PictureUtils.loadImage(coverView, movie.backdropPath);
        collapsingToolbarLayout.setTitle(movie.name);
        descriptionView.setText(movie.overview);
    }
}
