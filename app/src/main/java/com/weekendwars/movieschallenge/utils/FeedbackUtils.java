package com.weekendwars.movieschallenge.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.weekendwars.movieschallenge.R;

public final class FeedbackUtils {

    private FeedbackUtils() {
        throw new AssertionError("Utility classes shouldn't be instantiated");
    }

    /**
     * Displays an error snackbar message
     *
     * @param parent  the snackbar's parent view.
     * @param message the snackbar's message
     */
    public static void showErrorSnackbar(@NonNull final View parent, @NonNull final String message) {
        final Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(parent.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }
}
