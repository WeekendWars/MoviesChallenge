package com.weekendwars.movieschallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.holders.MovieViewHolder;
import com.weekendwars.movieschallenge.dto.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<Movie> mData = new ArrayList<>();
    private boolean loading;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                              final int itemViewType) {
        return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(itemViewType, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        holder.onBind(mData.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return loading ? mData.size() + 1 : mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        // If it's the last position renders the progress view. Otherwise renders the holder's view
        return position == mData.size() - 1 ? R.layout.holder_progress : R.layout.holder_movie;
    }

    /**
     * Sets the data being displayed to the adapter.
     *
     * @param data the data being displayed
     */
    public void setItems(@NonNull final List<Movie> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Attaches data to the actual list
     *
     * @param data the data being attached to the list
     */
    public void addItems(@NonNull final List<Movie> data) {
        final int lastIndex = mData.size();
        mData.addAll(data);

        notifyItemRangeChanged(lastIndex, mData.size());
    }

    /**
     * Displays or hides progress view
     *
     * @param show whether to display progress or not.
     */
    public void showProgress(final boolean show) {
        final int updatedIndex = show ? mData.size() : mData.size() - 1;
        loading = show;

        notifyItemChanged(updatedIndex);
    }

    @Override
    public String toString() {
        return "MoviesAdapter{"
                + "mData=" + mData
                + ", loading=" + loading
                + '}';
    }
}