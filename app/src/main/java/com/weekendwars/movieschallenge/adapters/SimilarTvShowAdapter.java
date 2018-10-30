package com.weekendwars.movieschallenge.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.adapters.holders.SimilarTvShowHolder;
import com.weekendwars.movieschallenge.dto.Movie;

import java.util.ArrayList;
import java.util.List;

public class SimilarTvShowAdapter extends RecyclerView.Adapter<SimilarTvShowHolder> {

    private final List<Movie> mData = new ArrayList<>();
    /* default */ MovieActionListener mListener;

    @NonNull
    @Override
    public SimilarTvShowHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                                  final int i) {
        return new SimilarTvShowHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.holder_similar_show, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SimilarTvShowHolder holder, final int position) {
        holder.onBind(mData.get(position));

        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    mListener.onMovieSelected(holder, mData.get(holder.getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Overrides current data with new one
     *
     * @param data the new data being displayed
     */
    public void setData(@NonNull final List<Movie> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Sets an action listener for listening user's action event
     *
     * @param listener the action listener
     */
    public void setMoviewActionListener(@NonNull final MovieActionListener listener) {
        mListener = listener;
    }
}
