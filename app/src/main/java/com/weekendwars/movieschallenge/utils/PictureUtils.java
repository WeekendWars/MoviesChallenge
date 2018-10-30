package com.weekendwars.movieschallenge.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PictureUtils {
    private static final String URL_PREFIX = "https://image.tmdb.org/t/p/w500/";

    private PictureUtils() {
        throw new AssertionError("Utility classes shouldn't be instantiated");
    }

    /**
     * Uses Picasso for loading an image url into an ImageView
     *
     * @param imageView the ImageView where to load the image
     * @param url       the image's url
     */
    public static void loadImage(@NonNull final ImageView imageView, @NonNull final String url) {
        Picasso.get().load(Uri.parse(URL_PREFIX + url)).into(imageView);
    }
}
