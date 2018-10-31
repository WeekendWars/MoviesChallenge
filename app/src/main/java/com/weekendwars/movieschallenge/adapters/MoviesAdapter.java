package com.weekendwars.movieschallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.holders.MovieViewHolder;
import com.weekendwars.movieschallenge.dto.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private static final int LAYOUT_PROGRESS = R.layout.holder_progress;
    private static final int LAYOUT_HOLDER = R.layout.holder_movie;
    private final List<Movie> mData = new ArrayList<>();
    private boolean loading;

    /*
        Default access modifier for avoiding hidden java costs by accessing a private member
        from an auto-generated method.
      */
    /* default */ MovieActionListener mListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                              final int itemViewType) {
        return new MovieViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(itemViewType, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        if (getItemViewType(position) == LAYOUT_HOLDER) {
            holder.onBind(mData.get(holder.getAdapterPosition()));

            // If someone is listening for action events we configure action's listener
            if (mListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {
                        /*
                            Uses adapter position because android cannot ensure data's position at this point
                            see: RecyclerView.Adapter.onBindViewHolder()'s documentation.
                         */
                        mListener.onMovieSelected(holder, mData.get(holder.getAdapterPosition()));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return loading ? mData.size() + 1 : mData.size();
    }

    @Override
    public int getItemViewType(final int position) {
        // If it's the last position and it's loading renders the progress view. Otherwise renders the holder's view
        return position == mData.size() && loading ? LAYOUT_PROGRESS : LAYOUT_HOLDER;
    }

    public void setMoviewActionListener(@NonNull final MovieActionListener listener) {
        mListener = listener;
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
        loading = show;

        notifyDataSetChanged();
    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    public String toString() {
        return "MoviesAdapter{"
                + "mData=" + mData
                + ", loading=" + loading
                + '}';
    }

}
