package com.weekendwars.movieschallenge.networking.interceptors;

import android.support.annotation.NonNull;

import com.weekendwars.movieschallenge.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Authentication interceptor intended to add the API KEY as a query param on every request
 */
public class AuthInterceptor implements Interceptor {

    private static final String QUERY_PARAM_API_KEY = "api_key";

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        Request request = chain.request();
        final HttpUrl url = request.url().newBuilder()
                .addQueryParameter(QUERY_PARAM_API_KEY, BuildConfig.API_KEY).build();

        request = request.newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
