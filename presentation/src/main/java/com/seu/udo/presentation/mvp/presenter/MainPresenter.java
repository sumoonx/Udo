package com.seu.udo.presentation.mvp.presenter;

import com.seu.udo.presentation.mvp.view.MainView;

import javax.inject.Inject;

/**
 * Author: Jeremy Xu on 2016/4/19 21:10
 * E-mail: jeremy_xm@163.com
 */
public class MainPresenter implements MvpPresenter<MainView> {

    private MainView mainView;

    @Inject
    public MainPresenter() {}

    @Override
    public void takeView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void dropView() {
        mainView = null;
    }
}
