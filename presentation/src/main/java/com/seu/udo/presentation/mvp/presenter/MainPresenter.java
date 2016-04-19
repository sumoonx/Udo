package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.view.MainMvpView;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/4/19 21:10
 * E-mail: jeremy_xm@163.com
 */
public class MainPresenter implements MvpPresenter<MainMvpView> {

    private MainMvpView mainView;

    @Inject
    public MainPresenter() {}

    @Override
    public void takeView(MainMvpView mainMvpView) {
        this.mainView = mainMvpView;
    }

    @Override
    public void dropView() {
        mainView = null;
    }
}
