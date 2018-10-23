package com.weekendwars.movieschallenge.adapters.holders;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.dto.Movie;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "FCBL_FIELD_COULD_BE_LOCAL",
        justification = "Since the view is being recycled we only instantiate views on holder's creation")
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private static final String URL_PREFIX = "https://image.tmdb.org/t/p/w500/";
    private final ImageView mCoverView;
    private final TextView mTitleView;
    private final TextView mDescriptionView;
    private final TextView mVotesView;

    /**
     * Default constructor
     *
     * @param itemView the view being attached
     */
    public MovieViewHolder(@NonNull final View itemView) {
        super(itemView);

        mCoverView = itemView.findViewById(R.id.coverView);
        mTitleView = itemView.findViewById(R.id.titleView);
        mDescriptionView = itemView.findViewById(R.id.descriptionView);
        mVotesView = itemView.findViewById(R.id.votesView);
    }

    /**
     * Called on holder's binding
     *
     * @param movie the data object being attached to the view
     */
    public void onBind(@NonNull final Movie movie) {
        Picasso.get().load(Uri.parse(URL_PREFIX + movie.backdropPath)).into(mCoverView);

        mTitleView.setText(movie.name);
        mDescriptionView.setText(movie.overview);
        mVotesView.setText(String.valueOf(movie.voteCount));
    }
}
