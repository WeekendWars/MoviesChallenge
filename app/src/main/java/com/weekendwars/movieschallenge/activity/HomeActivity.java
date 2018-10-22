package com.weekendwars.movieschallenge.activity;

import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.movieschallenge.R;
import com.weekendwars.movieschallenge.presenter.HomePresenter;
import com.weekendwars.movieschallenge.view.HomeView;

public class HomeActivity extends AbstractActivity<HomeView, HomePresenter> implements HomeView {

    @Override
    protected HomeView getMvpView() {
        return this;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }
}
