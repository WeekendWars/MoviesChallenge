package com.weekendwars.movieschallenge.networking;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weekendwars.movieschallenge.BuildConfig;
import com.weekendwars.movieschallenge.networking.interceptors.AuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitAdapter {

    public static final RetrofitAdapter INSTANCE = new RetrofitAdapter();
    public final Retrofit retrofit;

    private RetrofitAdapter() {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder().addInterceptor(new AuthInterceptor()).build();
    }
}