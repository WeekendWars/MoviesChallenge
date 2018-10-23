package com.weekendwars.movieschallenge.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.weekendwars.core.mvp.presenters.AbstractPresenter;
import com.weekendwars.movieschallenge.view.HomeView;

public class HomePresenter extends AbstractPresenter<HomeView> {

    @Override
    public void restoreState(@NonNull final Bundle savedState) {
        // Nothing to do yet
    }

    @NonNull
    @Override
    public Bundle getState() {
        return new Bundle();
    }
}
